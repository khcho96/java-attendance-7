package attendance.constant;

import java.util.Arrays;

public enum DangerState {
    DISMISSAL("제적", 6),
    MEETING("면담", 3),
    WARNING("경고", 2),
    NONE("", 0),
    ;

    private final String name;
    private final int absenceCount;

    DangerState(String name, int absenceCount) {
        this.name = name;
        this.absenceCount = absenceCount;
    }

    public String getName() {
        return name;
    }

    public static DangerState from(int absenceCount) {
        return Arrays.stream(values())
                .filter(state -> state.absenceCount <= absenceCount)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_INPUT.getErrorMessage()));
    }
}
