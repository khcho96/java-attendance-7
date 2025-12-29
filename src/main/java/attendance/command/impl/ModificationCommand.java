package attendance.command.impl;

import attendance.command.Command;
import attendance.domain.Crew;
import attendance.service.DemoService;
import attendance.util.InputParser;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class ModificationCommand implements Command {

    private final DemoService service;

    public ModificationCommand(DemoService service) {
        this.service = service;
    }

    @Override
    public void execute(LocalDate now) {
        String name = InputView.readNameForModification();
        Crew crew = service.getCrew(name);

        String rawDay = InputView.readDayOfMonth();
        LocalDate date = InputParser.parseDay(rawDay);

        String rawModificationTime = InputView.readModificationTime();
        LocalTime modificationTime = InputParser.parseTime(rawModificationTime);

        LocalDateTime dateTime = LocalDateTime.of(date, modificationTime);
        LocalDateTime oldDateTime = service.modifyAttendance(crew, dateTime);

        OutputView.print2(oldDateTime, dateTime, crew.getAttendanceState(date));
    }
}
