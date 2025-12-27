package attendance.constant;

import java.time.LocalDateTime;
import java.util.Arrays;

public enum AttendanceState {
    ABSENCE("결석", 30),
    LATE("지각", 5),
    ATTENDANCE("출석", 0),
    ;

    private final String name;
    private final long lateTime;

    AttendanceState(String name, long lateTime) {
        this.name = name;
        this.lateTime = lateTime;
    }

    public static AttendanceState from(LocalDateTime dateTime) {
        return Arrays.stream(values())
                .filter(state -> Standard.from(dateTime).getTime().plusMinutes(state.lateTime).isAfter(dateTime.toLocalTime()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage()));
    }
}
