package attendance.command.impl;

import attendance.command.Command;
import attendance.command.CommandResponse;
import attendance.domain.Crew;
import attendance.service.DemoService;
import attendance.util.InputParser;
import attendance.view.InputView;
import attendance.view.model.FeatureBModel;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class FeatureBCommand implements Command<FeatureBCommand> {

    private final DemoService service;

    public FeatureBCommand(DemoService service) {
        this.service = service;
    }

    @Override
    public CommandResponse execute(LocalDate now) {
        String name = InputView.readNameForModification();
        Crew crew = service.getCrew(name);

        String rawDay = InputView.readDayOfMonth();
        LocalDate date = InputParser.parseDay(rawDay);

        String rawModificationTime = InputView.readModificationTime();
        LocalTime modificationTime = InputParser.parseTime(rawModificationTime);

        LocalDateTime dateTime = LocalDateTime.of(date, modificationTime);
        LocalDateTime oldDateTime = service.modifyAttendance(crew, dateTime);

        return CommandResponse.keepGoing(new FeatureBModel(oldDateTime, dateTime, crew.getAttendanceState(date)));
    }
}
