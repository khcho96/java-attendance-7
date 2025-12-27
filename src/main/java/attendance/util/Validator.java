package attendance.util;

public final class Validator {

    private static final String CSV_FORMAT = "^ *([가-힣a-zA-Z]+-\\d+)+ *(, *([가-힣]+-\\d+)+ *)*$";
    private static final String NUMBER_FORMAT = "\\d+";

    private Validator() {}

    public static void validateNullOrBlank(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(INPUT_NULL_OR_BLANK_ERROR.getErrorMessage());
        }
    }

    public static void validateNumberFormat(String input) {
        if (!input.matches(NUMBER_FORMAT)) {
            throw new IllegalArgumentException(NUMBER_FORMAT_ERROR.getErrorMessage());
        }
    }

    public static void validateCsvFormat(String input) {
        if (!input.matches(CSV_FORMAT)) {
            throw new IllegalArgumentException(CSV_FORMAT_ERROR.getErrorMessage());
        }
    }

    public static void validateXxx(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(XXX_ERROR.getErrorMessage());
        }

        if (!input.matches(NUMBER_FORMAT)) {
            throw new IllegalArgumentException(XXX_ERROR.getErrorMessage());
        }

        if (!input.matches(CSV_FORMAT)) {
            throw new IllegalArgumentException(XXX_ERROR.getErrorMessage());
        }
    }
}
