package attendance.constant;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

public enum AttendanceState {

    ABSENCE(30, "제적"),
    LATE(5, "면담"),
    ATTENDANCE(0, "출석"),
    ;

    private final int lateMinutes;
    private final String name;

    AttendanceState(int lateMinutes, String name) {
        this.lateMinutes = lateMinutes;
        this.name = name;
    }

    public static AttendanceState of(LocalDate date, LocalTime time) {
        Standard standard = Standard.from(date);

        return Arrays.stream(values())
                .filter(danger -> standard.getStartTime().plusMinutes(danger.lateMinutes).isBefore(time))
                .findFirst()
                .orElse(ATTENDANCE);
    }

    public String getName() {
        return name;
    }
}
