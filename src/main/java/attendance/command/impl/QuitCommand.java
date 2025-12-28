package attendance.command.impl;

import attendance.command.Command;
import attendance.command.CommandResponse;
import java.time.LocalDate;

public class QuitCommand implements Command<QuitCommand> {

    @Override
    public CommandResponse execute(LocalDate now) {
        return CommandResponse.exit();
    }
}
