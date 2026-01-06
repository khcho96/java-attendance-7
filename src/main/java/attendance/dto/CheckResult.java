package attendance.dto;

import attendance.constant.Check;
import java.time.LocalDateTime;

public record CheckResult(LocalDateTime dateTime, Check check) {

    public static CheckResult of(LocalDateTime dateTime, Check check) {
        return new CheckResult(dateTime, check);
    }
}
