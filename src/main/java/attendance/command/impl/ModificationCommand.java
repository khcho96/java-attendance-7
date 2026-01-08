package attendance.command.impl;

import attendance.command.Command;
import attendance.service.AttendanceService;
import attendance.util.InputParser;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.time.LocalDate;
import java.time.LocalTime;

public class ModificationCommand implements Command {

    private final AttendanceService service;

    public ModificationCommand(AttendanceService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        String name = InputParser.parseName(InputView.readModifiedName());
        service.validateModificationPossible(name);

        LocalDate time = InputParser.parseTime(InputView.readTime());
        service.validateModificationPossible(time);

        LocalTime time = InputParser.parseTime(InputView.readTime());
        service.validateModificationPossible(time);

        service.modify(name, now, time);

        OutputView.printCheckResult(now, time);
    }
}
