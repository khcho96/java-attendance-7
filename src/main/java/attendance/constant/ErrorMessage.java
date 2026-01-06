package attendance.constant;

public enum ErrorMessage {

    FORMAT_ERROR("잘못된 형식을 입력하였습니다."),
    NO_ATTENDANCE_DAY_ERROR("%s은 등교일이 아닙니다."),
    NO_EXIST_NAME_ERROR("등록되지 않은 닉네임입니다."),
    ALREADY_ATTENDANCE_ERROR("이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해주세요."),
    NO_OPERATION_TIME_ERROR("캠퍼스 운영 시간에만 출석이 가능합니다."),
    FUTURE_DAY_ERROR("아직 수정할 수 없습니다."),
    NO_EXIST_ATTENDANCE_CHECK_RECORD("출석 기록이 없습니다."),
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
