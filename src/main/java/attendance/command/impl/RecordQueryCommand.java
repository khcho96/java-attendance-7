package attendance.command.impl;

import attendance.command.Command;
import attendance.domain.Crew;
import attendance.service.AttendanceService;
import attendance.util.InputParser;
import attendance.view.InputView;
import attendance.view.OutputView;

public class RecordQueryCommand implements Command {

    private final AttendanceService service;

    public RecordQueryCommand(AttendanceService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        String name = InputParser.parseName(InputView.readName());
        Crew crew = service.getAttendanceRecords(name);

        OutputView.printRecords(crew);
    }
}
