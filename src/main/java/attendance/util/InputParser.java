package attendance.util;

import java.time.LocalDate;
import java.time.LocalTime;

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
        return LocalDate.of(2024, 12, day);
    }
}
