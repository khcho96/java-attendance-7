package attendance.command.impl;

import attendance.command.Command;
import attendance.domain.Crew;
import attendance.service.DemoService;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.time.LocalDate;

public class HistoryCommand implements Command {

    private final DemoService service;

    public HistoryCommand(DemoService service) {
        this.service = service;
    }

    @Override
    public void execute(LocalDate now) {
        String name = InputView.readName();
        Crew crew = service.getCrew(name);

        OutputView.print3(crew, now);
    }
}
