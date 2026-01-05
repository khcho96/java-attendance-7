package attendance.constant;

import java.time.DayOfWeek;
import java.time.LocalDate;

public enum Holiday {

    SATURDAY(DayOfWeek.SATURDAY, LocalDate.of(1, 1, 1)),
    SUNDAY(DayOfWeek.SATURDAY, LocalDate.of(1, 1, 1)),
    CHRISTMAS(DayOfWeek.WEDNESDAY, LocalDate.of(2024, 12, 25)),
    NONE(null, null)
    ;

    private final DayOfWeek dayOfWeek;
    private final LocalDate date;

    Holiday(DayOfWeek dayOfWeek, LocalDate date) {
        this.dayOfWeek = dayOfWeek;
        this.date = date;
    }

    public static Holiday from(LocalDate date) {
        if (date.getDayOfWeek() == DayOfWeek.SATURDAY) {
            return SATURDAY;
        }

        if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return SUNDAY;
        }

        if (date.isEqual(CHRISTMAS.date)) {
            return CHRISTMAS;
        }

        return NONE;
    }
}
