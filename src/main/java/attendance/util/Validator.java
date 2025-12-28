package attendance.util;

import attendance.constant.ErrorMessage;
import java.time.LocalTime;

public final class Validator {

    private static final String CSV_FORMAT = "^ *([가-힣a-zA-Z]+-\\d+)+ *(, *([가-힣]+-\\d+)+ *)*$";
    private static final String NUMBER_FORMAT = "\\d+";
    private static final String TIME_FORMAT = "\\d{2}:\\d{2}";
    private static final LocalTime START_TIME = LocalTime.parse("08:00");
    private static final LocalTime END_TIME = LocalTime.parse("23:00");

    private Validator() {}

    public static void validateTimeFormat(String attendanceTime) {
        if (!attendanceTime.matches(TIME_FORMAT)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage());
        }
    }

    public static void validatePossibleTime(String attendanceTime) {
        LocalTime time = LocalTime.parse(attendanceTime);
        if (time.isBefore(START_TIME) || time.isAfter(END_TIME)) {
            throw new IllegalArgumentException(ErrorMessage.IMPOSSIBLE_TIME.getErrorMessage());
        }
    }

    public static void validateDay(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage());
        }
    }
}
