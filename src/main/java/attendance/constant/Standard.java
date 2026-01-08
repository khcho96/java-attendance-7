package attendance.constant;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public enum Standard {

    MONDAY(LocalTime.of(13,0)),
    OTHERS(LocalTime.of(10,0)),
    ;

    private final LocalTime startTime;

    Standard(LocalTime startTime) {
        this.startTime = startTime;
    }

    public static Standard from(LocalDate date) {

        if (date.getDayOfWeek().equals(DayOfWeek.MONDAY) && Holiday.from(date).equals(Holiday.NONE)) {
            return MONDAY;
        }

        return OTHERS;
    }

    public LocalTime getStartTime() {
        return startTime;
    }
}
