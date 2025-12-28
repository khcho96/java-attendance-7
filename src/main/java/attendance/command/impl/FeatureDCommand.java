//package attendance.command.impl;
//
//import attendance.command.Command;
//import attendance.command.CommandResponse;
//import attendance.service.DemoService;
//import attendance.view.InputView;
//import attendance.view.model.FeatureDModel;
//import java.time.LocalDate;
//
//public class FeatureDCommand implements Command<FeatureDCommand> {
//
//    private final DemoService service;
//
//    public FeatureDCommand(DemoService service) {
//        this.service = service;
//    }
//
//    @Override
//    public CommandResponse execute(LocalDate now) {
//        String input = InputView.readEnterForFeatureD();
//        String msg = service.executeFeatureD(input);
//        return CommandResponse.keepGoing(new FeatureDModel("기능 D", msg));
//    }
//}
