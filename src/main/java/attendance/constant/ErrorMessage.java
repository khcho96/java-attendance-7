package attendance.constant;

public enum ErrorMessage {

    FORMAT_ERROR("잘못된 형식을 입력하였습니다."),
    ERROR("%d월 %d일 %s요일은 등교일이 아닙니다."),
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
