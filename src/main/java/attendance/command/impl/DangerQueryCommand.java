package attendance.command.impl;

import attendance.command.Command;
import attendance.domain.Crew;
import attendance.service.AttendanceService;
import attendance.view.OutputView;
import java.util.List;

public class DangerQueryCommand implements Command {

    private final AttendanceService service;

    public DangerQueryCommand(AttendanceService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        List<Crew> dangers = service.getDangers();

        OutputView.printDangers(dangers);
    }
}
