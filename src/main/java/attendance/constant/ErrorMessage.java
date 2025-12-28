package attendance.constant;

public enum ErrorMessage {

    INVALID_INPUT("잘못된 형식을 입력하였습니다."),
    NO_ATTENDANCE_DAY("%d월 %d일 %s요일은 등교일이 아닙니다."),
    INVALID_NAME("등록되지 않은 닉네임입니다."),
    ALREADY_ATTEND("이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해주세요."),
    IMPOSSIBLE_TIME("캠퍼스 운영 시간에만 출석이 가능합니다.");

    private static final String ERROR_MESSAGE_PREFIX = "[ERROR] ";
    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage(Object... args) {
        return ERROR_MESSAGE_PREFIX + String.format(errorMessage, args);
    }
}
