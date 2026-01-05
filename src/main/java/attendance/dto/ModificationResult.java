package attendance.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record ModificationResult(LocalDate date, LocalTime oldTime, LocalTime newTime) {

    public static ModificationResult of(LocalDate date, LocalTime oldTime, LocalTime newTime) {
        return new ModificationResult(date, oldTime, newTime);
    }
}
