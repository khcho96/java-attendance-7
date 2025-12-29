package attendance.command;

import java.time.LocalDate;

public interface Command {
    void execute(LocalDate now);
}
