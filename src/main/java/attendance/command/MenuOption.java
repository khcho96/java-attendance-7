package attendance.command;

import attendance.constant.ErrorMessage;
import java.util.Arrays;

public enum MenuOption {
    A("1"),
    B("2"),
    C("3"),
    D("4"),
    QUIT("Q");

    private final String command;

    MenuOption(String command) {
        this.command = command;
    }

    public static MenuOption from(String command) {
        return Arrays.stream(values())
                .filter(opt -> opt.command.equals(command))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.FORMAT_ERROR.getErrorMessage()));
    }
}
