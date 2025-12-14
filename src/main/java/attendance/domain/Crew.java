package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Crew {

    private String name;
    private DangerState state;
    private List<Attendance> attendances;
    private int lateCount;
    private int absenceCount;

    public Crew(String name) {
        this.name = name;
        attendances = new ArrayList<>();
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
                ", state=" + state +
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
        if (time.isAfter(lateTime)) {
            attendanceState = "지각";
            lateCount++;
        }
        if (time.isAfter(absenceTime)) {
            attendanceState = "결석";
            absenceCount++;
        }
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

                return "\n" + month + "월 " + day + "일 " + dayOfWeek + "요일 "
                        + String.format("%02d:%02d", hour, minute) + " (" + state + ")";
            }
        }
        return null;
    }
}
