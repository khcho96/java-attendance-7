package attendance.command.impl;

import attendance.command.Command;
import attendance.service.AttendanceService;

public class FeatureDCommand implements Command {

    private final AttendanceService service;

    public FeatureDCommand(AttendanceService service) {
        this.service = service;
    }

    @Override
    public void execute() {

    }
}
