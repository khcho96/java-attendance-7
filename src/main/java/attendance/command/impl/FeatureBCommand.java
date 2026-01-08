package attendance.command.impl;

import attendance.command.Command;
import attendance.service.AttendanceService;

public class FeatureBCommand implements Command {

    private final AttendanceService service;

    public FeatureBCommand(AttendanceService service) {
        this.service = service;
    }

    @Override
    public void execute() {

    }
}
