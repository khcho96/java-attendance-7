package attendance.command.impl;

import attendance.command.Command;
import attendance.command.CommandResponse;

public class QuitCommand implements Command<QuitCommand> {

    @Override
    public CommandResponse execute() {
        return CommandResponse.exit();
    }
}
