package attendance.constant;

import java.time.LocalTime;

public enum OperationTime {

    START(LocalTime.of(8,0)),
    END(LocalTime.of(23,0))
    ;

    private final LocalTime time;

    OperationTime(LocalTime time) {
        this.time = time;
    }

    public LocalTime getTime() {
        return time;
    }
}
