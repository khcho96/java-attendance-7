package attendance.command;

import java.time.LocalDate;

public interface Command<T extends Command<?>> {
    CommandResponse execute(LocalDate now);
}
