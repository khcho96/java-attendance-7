package attendance.domain;

import attendance.constant.Danger;
import attendance.constant.ErrorMessage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class Crew {

    private final String name;
    private final Attendance attendance;

    private Crew(String name, Attendance attendance) {
        this.name = name;
        this.attendance = attendance;
    }

    public static Crew of(String name, Map<LocalDate, LocalTime> localDateTimes) {
        return new Crew(name, Attendance.from(localDateTimes));
    }

    public String getName() {
        return name;
    }

    public void validateCheckPossible(LocalDate date) {
        if (attendance.contains(date)) {
            throw new IllegalArgumentException(ErrorMessage.ALREADY_ATTENDANCE_ERROR.getErrorMessage());
        }
    }

    public void check(LocalDate date, LocalTime time) {
        attendance.check(date, time);
    }

    public LocalTime modify(LocalDate date, LocalTime newTime) {
        return attendance.modify(date, newTime);
    }

    public Map<LocalDate, LocalTime> getAttendanceRecords() {
        return attendance.getRecords();
    }

    public int getAttendanceCount() {
        return attendance.getAttendanceCount();
    }

    public int getLateCount() {
        return attendance.getLateCount();
    }

    public int getAbsenceCount() {
        return attendance.getAbsenceCount();
    }

    public Danger getDangerState() {
        return Danger.from(getAbsenceLateCount());
    }

    public int getAbsenceLateCount() {
        return getAbsenceCount() + getLateCount() / 3;
    }
}
