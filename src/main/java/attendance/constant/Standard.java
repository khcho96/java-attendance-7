package attendance.constant;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

public enum Standard {

    MONDAY(DayOfWeek.MONDAY, LocalTime.parse("13:00")),
    OTHERS(null, LocalTime.parse("10:00")),
    ;

    private final DayOfWeek dayOfWeek;
    private final LocalTime time;

    Standard(DayOfWeek dayOfWeek, LocalTime time) {
        this.dayOfWeek = dayOfWeek;
        this.time = time;
    }

    public static Standard from(LocalDateTime dateTime) {
        if (dateTime.getDayOfWeek().equals(DayOfWeek.MONDAY)) {
            return MONDAY;
        }

        return OTHERS;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getTime() {
        return time;
    }
}
