package attendance.constant;

import java.time.DayOfWeek;
import java.time.LocalDate;

public enum Holiday {

    HOLI_DAY,
    NONE;

    public static Holiday from(LocalDate date) {
        if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY) || date.isEqual(LocalDate.of(2024,12,25))) {
            return HOLI_DAY;
        }

        return NONE;
    }
}
