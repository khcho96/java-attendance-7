package attendance.command.impl;

import attendance.command.Command;
import attendance.command.CommandResponse;
import attendance.domain.Crew;
import attendance.service.DemoService;
import attendance.view.InputView;
import attendance.view.model.FeatureCModel;
import java.time.LocalDate;

public class FeatureCCommand implements Command<FeatureCCommand> {

    private final DemoService service;

    public FeatureCCommand(DemoService service) {
        this.service = service;
    }

    @Override
    public CommandResponse execute(LocalDate now) {
        String name = InputView.readName();
        Crew crew = service.getCrew(name);

        return CommandResponse.keepGoing(new FeatureCModel(crew, now));
    }
}
