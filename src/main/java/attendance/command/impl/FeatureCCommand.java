package attendance.command.impl;

import attendance.command.Command;
import attendance.command.CommandResponse;
import attendance.service.DemoService;
import attendance.util.Retry;
import attendance.view.InputView;
import attendance.view.model.FeatureCModel;
import java.util.List;

public class FeatureCCommand implements Command<FeatureCCommand> {

    private final DemoService service;

    public FeatureCCommand(DemoService service) {
        this.service = service;
    }

    @Override
    public CommandResponse execute() {
        String input = InputView.readCommaSeparatedWordsForFeatureC();
        List<String> words = Retry.retryUntilSuccess(() -> service.executeFeatureC(input));
        return CommandResponse.keepGoing(new FeatureCModel(words));
    }
}
