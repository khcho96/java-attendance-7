package attendance.command.impl;

import attendance.command.Command;
import attendance.command.CommandResponse;
import attendance.service.DemoService;
import attendance.util.Retry;
import attendance.view.InputView;
import attendance.view.model.FeatureAModel;

public class FeatureACommand implements Command<FeatureACommand> {

    private final DemoService service;

    public FeatureACommand(DemoService service) {
        this.service = service;
    }

    @Override
    public CommandResponse execute() {
        String string = InputView.readNameForFeatureA();
        String name = Retry.retryUntilSuccess(() -> service.executeFeatureA(string));
        return CommandResponse.keepGoing(new FeatureAModel(name));
    }
}
