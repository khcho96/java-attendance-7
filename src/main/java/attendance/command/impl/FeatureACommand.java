package attendance.command.impl;

import attendance.command.Command;
import attendance.service.AttendanceService;

public class FeatureACommand implements Command {

    private final AttendanceService service;

    public FeatureACommand(AttendanceService service) {
        this.service = service;
    }

    @Override
    public void execute() {

    }
}
