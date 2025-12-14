package attendance.util;

import static attendance.constant.ErrorMessage.FORMAT_ERROR;

public final class Validator {

    private static final String CSV_FORMAT = "^ *(\\[[가-힣a-zA-Z]+-\\d+])+ *(, *(\\[[가-힣a-zA-Z]+-\\d+])+ *)*$";
    private static final String CHOICE = " *[1234Q] *";

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
}
