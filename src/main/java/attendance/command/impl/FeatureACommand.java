package attendance.command.impl;

import attendance.command.Command;
import attendance.command.CommandResponse;
import attendance.constant.ErrorMessage;
import attendance.domain.Crew;
import attendance.service.DemoService;
import attendance.util.InputParser;
import attendance.view.InputView;
import attendance.view.model.FeatureAModel;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class FeatureACommand implements Command<FeatureACommand> {

    private final DemoService service;

    public FeatureACommand(DemoService service) {
        this.service = service;
    }

    @Override
    public CommandResponse execute(LocalDate now) {
        if (now.getDayOfWeek().equals(DayOfWeek.SATURDAY) || now.getDayOfWeek().equals(DayOfWeek.SUNDAY) || now.isEqual(LocalDate.of(2024,12,25))) {
            throw new IllegalArgumentException(ErrorMessage.NO_ATTENDANCE_DAY.getErrorMessage(now.getMonth(), now.getDayOfMonth(), now.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREA)));
        }

        String name = InputView.readName();
        Crew crew = service.getCrew(name, now);

        String rawAttendanceTime = InputView.readAttendanceTime();
        LocalTime attendanceTime = InputParser.parseTime(rawAttendanceTime);
        service.registerAttendance(crew, now, attendanceTime);

        return CommandResponse.keepGoing(new FeatureAModel(LocalDateTime.of(now, attendanceTime), crew.getAttendanceState(now)));
    }
}
