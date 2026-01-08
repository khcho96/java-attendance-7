package attendance.command.impl;

import attendance.command.Command;
import attendance.service.AttendanceService;

public class FeatureCCommand implements Command {

    private final AttendanceService service;

    public FeatureCCommand(AttendanceService service) {
        this.service = service;
    }

    @Override
    public void execute() {

    }
}
