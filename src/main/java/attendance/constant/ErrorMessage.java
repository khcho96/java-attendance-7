package attendance.constant;

public enum ErrorMessage {

    INVALID_INPUT("잘못된 형식을 입력하였습니다."),
    ;

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(Object... args) {
        return ERROR_MESSAGE_PREFIX + String.format(errorMessage, args);
    }
}
