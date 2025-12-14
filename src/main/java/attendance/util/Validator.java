package attendance.util;

import static attendance.constant.ErrorMessage.FORMAT_ERROR;

public final class Validator {

    private static final String CSV_FORMAT = "^ *(\\[[가-힣a-zA-Z]+-\\d+])+ *(, *(\\[[가-힣a-zA-Z]+-\\d+])+ *)*$";
    private static final String CHOICE = " *[1234Q] *";
    private static final String TIME_FORMAT = "\\d{2}:\\d{2}";

    private Validator() {
    }

    public static void validateCsvFormat(String input) {
        if (!input.matches(CSV_FORMAT)) {
            throw new IllegalArgumentException(FORMAT_ERROR.getErrorMessage());
        }
    }

    public static void validateChoiceFormat(String rawChoice) {
        if (!rawChoice.matches(CHOICE)) {
            throw new IllegalArgumentException(FORMAT_ERROR.getErrorMessage());
        }
    }

    public static void validateTimeFormat(String rawTime) {
        if (!rawTime.matches(TIME_FORMAT)) {
            throw new IllegalArgumentException(FORMAT_ERROR.getErrorMessage());
        }

        String[] split = rawTime.split(":");
        int hour = Integer.parseInt(split[0]);
        int min = Integer.parseInt(split[1]);
        if (hour < 0 || hour > 23 || min < 0 || min > 59) {
            throw new IllegalArgumentException(FORMAT_ERROR.getErrorMessage());
        }
    }
}
