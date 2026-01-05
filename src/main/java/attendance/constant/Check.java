package attendance.constant;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

public enum Check {

    ABSENCE(30, "결석"),
    LATE(5, "지각"),
    ATTENDANCE(0, "출석"),
    ;

    private final int lateMinutes;
    private final String name;

    Check(int lateMinutes, String name) {
        this.lateMinutes = lateMinutes;
        this.name = name;
    }

    public static Check from(LocalDateTime dateTime) {
        LocalTime time = dateTime.toLocalTime();
        LocalTime standardTime = Standard.from(dateTime.toLocalDate()).getStartTime();

        return Arrays.stream(values())
                .filter(check -> time.isAfter(standardTime.plusMinutes(check.lateMinutes)))
                .findFirst()
                .orElse(ATTENDANCE);
    }

    public String getName() {
        return name;
    }
}
