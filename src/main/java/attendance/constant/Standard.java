package attendance.constant;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public enum Standard {

    MONDAY(DayOfWeek.MONDAY, LocalTime.of(13,0), LocalTime.of(23,0)),
    OTHER(null, LocalTime.of(10,0), LocalTime.of(23,0)),
    ;

    private final DayOfWeek dayOfWeek;
    private final LocalTime startTime;
    private final LocalTime endTime;

    Standard(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.dayOfWeek = dayOfWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public static Standard from(LocalDate date) {
        if (date.getDayOfWeek() == DayOfWeek.MONDAY) {
            return MONDAY;
        }

        return OTHER;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
