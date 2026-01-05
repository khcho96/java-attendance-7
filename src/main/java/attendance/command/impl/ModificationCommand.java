package attendance.command.impl;

import attendance.command.Command;
import attendance.service.AttendanceService;

public class ModificationCommand implements Command {

    private final AttendanceService service;

    public ModificationCommand(AttendanceService service) {
        this.service = service;
    }

    @Override
    public void execute() {

    }
}
