package attendance.command;

import attendance.view.model.QuitModel;
import attendance.view.model.ViewModel;

public record CommandResponse(Flow flow, ViewModel model) {

    public static CommandResponse keepGoing(ViewModel model) {
        return new CommandResponse(Flow.CONTINUE, model);
    }

    public static CommandResponse exit() {
        return new CommandResponse(Flow.EXIT, new QuitModel());
    }
}
