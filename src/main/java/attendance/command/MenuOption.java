package attendance.command;

import attendance.constant.ErrorMessage;
import java.util.Arrays;

public enum MenuOption {
    A("1"),
    B("2"),
    C("3"),
    D("4"),
    QUIT("Q");

    private final String code;

    MenuOption(String code) {
        this.code = code;
    }

    public static MenuOption from(String raw) {
        String normalized = raw.strip();
        return Arrays.stream(values())
                .filter(opt -> opt.code.equals(normalized))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage()));
    }
}
