package attendance.constant;

public enum ErrorMessage {

    FORMAT_ERROR("잘못된 형식을 입력하였습니다."),
    EXCEED_ERROR("재고 수량을 초과하여 구매할 수 없습니다. 다시 입력해 주세요."),
    INVALID_ERROR("잘못된 입력입니다. 다시 입력해 주세요.");

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return ERROR_MESSAGE_PREFIX + errorMessage;
    }
}
