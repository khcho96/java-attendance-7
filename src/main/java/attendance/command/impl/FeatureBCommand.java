package attendance.command.impl;

import attendance.command.Command;
import attendance.command.CommandResponse;
import attendance.service.DemoService;
import attendance.util.Retry;
import attendance.view.InputView;
import attendance.view.model.FeatureBModel;

public class FeatureBCommand implements Command<FeatureBCommand> {

    private final DemoService service;

    public FeatureBCommand(DemoService service) {
        this.service = service;
    }

    @Override
    public CommandResponse execute() {
        String input = InputView.readTwoIntegersForFeatureB();
        int[] ab = Retry.retryUntilSuccess(() -> service.executeFeatureB(input));
        int sum = ab[0]+ ab[1];
        return CommandResponse.keepGoing(new FeatureBModel(ab[0], ab[1], sum));
    }
}
