package attendance.util;

import main.java.lotto.constant.ErrorMessage;

public final class NumberConvertor {

    public static int convertToNumber(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.FORMAT_ERROR.getErrorMessage());
        }
    }
}
