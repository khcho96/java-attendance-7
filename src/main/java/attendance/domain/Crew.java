package attendance.domain;

import attendance.constant.Check;
import attendance.constant.Danger;
import attendance.constant.ErrorMessage;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Crew {

    private final String name;
    private final List<Attendance> attendances;

    private Crew(String name) {
        this.name = name;
        this.attendances = new ArrayList<>();
    }

    public static Crew from(String crewName) {
        return new Crew(crewName);
    }

    public Check addAttendanceRecord(LocalDateTime dateTime) {
        attendances.add(Attendance.from(dateTime));
        return Check.from(dateTime);
    }

    public String getName() {
        return name;
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

    public boolean containsDate(LocalDate now) {
        return attendances.stream()
                .anyMatch(attendance -> attendance.getDate().isEqual(now));
    }

    public LocalTime modifyRecord(LocalDate date, LocalTime time) {
        Attendance record = attendances.stream()
                .filter(attendance -> attendance.getDate().isEqual(date))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        ErrorMessage.NO_EXIST_ATTENDANCE_CHECK_RECORD.getErrorMessage()));
        return record.modifyTime(time);
    }

    public List<Attendance> getAttendances() {
        return attendances;
    }

    public int getAttendanceCount() {
        int count = 0;
        for (Attendance attendance : attendances) {
            if (attendance.isAttendance()) {
                count++;
            }
        }
        return count;
    }

    public int getLateCount() {
        int count = 0;
        for (Attendance attendance : attendances) {
            if (attendance.isLate()) {
                count++;
            }
        }
        return count;
    }

    public int getAbsenceCount() {
        int count = 0;
        for (Attendance attendance : attendances) {
            if (attendance.isAbsence()) {
                count++;
            }
        }
        return count;
    }

    public boolean isDangerCrew() {
        return getAbsenceCount() + getLateCount() / 3 >= 2;
    }

    public String getDangerState() {
        return Danger.from(getAbsenceCount() + getLateCount() / 3).getName();
    }

    public int getAbsenceLateCount() {
        return getAbsenceCount() + getLateCount() / 3;
    }
}
