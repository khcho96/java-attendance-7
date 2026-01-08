package attendance.util;

import attendance.constant.ErrorMessage;

public final class Validator {

    private static final String DAY_FORMAT = "^[1-9]|[1-2]\\d|3[0-1]$";
    private static final String TIME_FORMAT = "([01]\\d|2[0-3]):[0-5]\\d";

    private Validator() {}

    public static void validateDayFormat(String rawInput) {
        if (!rawInput.matches(DAY_FORMAT)) {
            throw new IllegalArgumentException(ErrorMessage.FORMAT_ERROR.getErrorMessage());
        }
    }

    public static void validateTimeFormat(String rawInput) {
        if (!rawInput.matches(TIME_FORMAT)) {
            throw new IllegalArgumentException(ErrorMessage.FORMAT_ERROR.getErrorMessage());
        }
    }
}
