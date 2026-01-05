package attendance.command.impl;

import attendance.command.Command;
import attendance.service.AttendanceService;

public class RecordQueryCommand implements Command {

    private final AttendanceService service;

    public RecordQueryCommand(AttendanceService service) {
        this.service = service;
    }

    @Override
    public void execute() {

    }
}
