package attendance.command.impl;

import attendance.command.Command;
import attendance.service.AttendanceService;

public class DangerQueryCommand implements Command {

    private final AttendanceService service;

    public DangerQueryCommand(AttendanceService service) {
        this.service = service;
    }

    @Override
    public void execute() {

    }
}
