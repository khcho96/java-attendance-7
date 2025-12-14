package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Crew implements Comparable<Crew> {

    private String name;
    private String dangerState;
    private List<Attendance> attendances;
    private int attendanceCount;
    private int lateCount;
    private int absenceCount;

    public Crew(String name) {
        this.name = name;
        attendances = new ArrayList<>();
    }

    public Crew(String name, List<Attendance> attendances, int lateCount, int absenceCount, String dangerState) {
        this.name = name;
        this.attendances = attendances;
        this.lateCount = lateCount;
        this.absenceCount = absenceCount;
        this.dangerState = dangerState;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Crew crew = (Crew) object;
        return Objects.equals(name, crew.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public void registerDateAndTime(LocalDate date, LocalTime time) {
        String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);

        String attendanceState = getAttendanceState(time, dayOfWeek);

        attendances.add(new Attendance(date, dayOfWeek, time, attendanceState));
    }

    @Override
    public String toString() {
        return "Crew{" +
                "name='" + name + '\'' +
                ", state=" + dangerState +
                ", attendances=" + attendances +
                ", lateCount=" + lateCount +
                ", absenceCount=" + absenceCount +
                '}';
    }

    public String getName() {
        return name;
    }

    public boolean containsDate(LocalDate date) {
        for (Attendance attendance : attendances) {
            if (attendance.getDate().isEqual(date)) {
                return true;
            }
        }
        return false;
    }

    public Crew registerAttendance(LocalTime newTime, LocalDate nowDate) {
        String dayOfWeek = nowDate.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);
        attendances.add(new Attendance(nowDate, dayOfWeek, newTime, getAttendanceState(newTime, dayOfWeek)));
        return this;
    }

    private String getAttendanceState(LocalTime time, String dayOfWeek) {
        LocalTime lateTime = LocalTime.parse("10:05");
        LocalTime absenceTime = LocalTime.parse("10:30");
        if (dayOfWeek.equals("월")) {
            lateTime = LocalTime.parse("13:05");
            absenceTime = LocalTime.parse("13:30");
        }

        String attendanceState = "출석";

        if (time.isAfter(absenceTime)) {
            attendanceState = "결석";
            absenceCount++;
            return attendanceState;
        }

        if (time.isAfter(lateTime)) {
            attendanceState = "지각";
            lateCount++;
            return attendanceState;
        }

        attendanceCount++;

        return attendanceState;
    }

    public String getInfoAt(LocalDate nowDate) {
        for (Attendance attendance : attendances) {
            if (attendance.getDate().isEqual(nowDate)) {
                int month = attendance.getDate().getMonthValue();
                int day = attendance.getDate().getDayOfMonth();
                String dayOfWeek = attendance.getDayOfWeek();
                int hour = attendance.getTime().getHour();
                int minute = attendance.getTime().getMinute();
                String state = attendance.getState();

                if (state.equals("결석")) {
                    return month + "월 " + day + "일 " + dayOfWeek + "요일 "
                            + "--:--" + " (" + state + ")";
                }

                return month + "월 " + day + "일 " + dayOfWeek + "요일 "
                        + String.format("%02d:%02d", hour, minute) + " (" + state + ")";
            }
        }
        return null;
    }

    public Crew clone() {
        List<Attendance> copyAttendances = new ArrayList<>();
        for (Attendance attendance : attendances) {
            copyAttendances.add(attendance.clone());
        }
        return new Crew(this.name, copyAttendances, this.lateCount, this.absenceCount, this.dangerState);
    }

    public void modifyAttendance(LocalTime newTime, LocalDate modifiedDate) {
        String dayOfWeek = modifiedDate.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);
        for (Attendance attendance : attendances) {
            if (attendance.getDate().isEqual(modifiedDate)) {
                attendance.modify(modifiedDate, dayOfWeek, newTime, getAttendanceState(newTime, dayOfWeek));
                return;
            }
        }
        attendances.add(new Attendance(modifiedDate, dayOfWeek, newTime, getAttendanceState(newTime, dayOfWeek)));
    }

    public int getLateCount() {
        return lateCount;
    }

    public int getAbsenceCount() {
        return absenceCount;
    }

    public int getAttendanceCount() {
        return attendanceCount;
    }

    public String getDangerState() {
        return dangerState;
    }

    public List<LocalDate> getDates() {
        List<LocalDate> dates = new ArrayList<>();
        Collections.sort(attendances);
        for (Attendance attendance : attendances) {
            dates.add(attendance.getDate());
        }
        return dates;
    }

    public void addAbsenceDate(List<LocalDate> allDates) {
        for (LocalDate date : allDates) {
            boolean isContain = false;
            for (Attendance attendance : attendances) {
                if (attendance.getDate().isEqual(date)) {
                    isContain = true;
                    break;
                }
            }
            if (!isContain) {
                this.absenceCount++;
                String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);
                attendances.add(new Attendance(date, dayOfWeek, LocalTime.of(0,0), "결석"));
            }
        }
    }

    public void setDangerStatus() {
        int absenceCount = this.absenceCount + lateCount / 3;

        if (absenceCount > 5) {
            dangerState = "제적";
            return;
        }

        if (absenceCount >= 3) {
            dangerState = "면담";
            return;
        }

        if (absenceCount >= 2) {
            dangerState = "경고";
            return;
        }

        dangerState = "";
    }

    @Override
    public int compareTo(Crew o) {
        if (this.absenceCount > o.absenceCount) {
            return -1;
        }

        if (this.absenceCount == o.absenceCount) {
            if (this.lateCount > o.lateCount) {
                return -1;
            }

            if (this.lateCount < o.lateCount) {
                return 1;
            }

            return 0;
        }

        return 0;
    }
}
