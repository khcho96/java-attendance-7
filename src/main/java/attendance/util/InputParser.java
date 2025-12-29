package attendance.util;

import attendance.constant.ErrorMessage;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

public final class InputParser {

    private static final String DELIMITER = ",";
    private static final String FIRST_DELIMITER = ",";
    private static final String SECOND_DELIMITER = "-";

    private InputParser() {
    }

    public static LocalTime parseTime(String attendanceTime) {
        Validator.validateTimeFormat(attendanceTime);
        Validator.validatePossibleTime(attendanceTime);
        return LocalTime.parse(attendanceTime);
    }

    public static LocalDate parseDay(String rawDay) {
        int day = NumberConvertor.convertToNumber(rawDay);
        Validator.validateDay(day);
        LocalDate date = LocalDate.of(2024, 12, day);
        if (date.getDayOfWeek().equals(DayOfWeek.SATURDAY) || date.getDayOfWeek().equals(DayOfWeek.SUNDAY) || date.isEqual(LocalDate.of(2024,12,25))) {
            throw new IllegalArgumentException(
                    ErrorMessage.NO_ATTENDANCE_DAY.getErrorMessage(date.getMonthValue(), date.getDayOfMonth(), date.getDayOfWeek().getDisplayName(
                            TextStyle.NARROW, Locale.KOREA)));
        }
        return date;
    }
}
