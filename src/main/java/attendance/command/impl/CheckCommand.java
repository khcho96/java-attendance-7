package attendance.command.impl;

import attendance.command.Command;
import attendance.service.AttendanceService;

public class CheckCommand implements Command {

    private final AttendanceService service;

    public CheckCommand(AttendanceService service) {
        this.service = service;
    }

    @Override
    public void execute() {

    }
}
