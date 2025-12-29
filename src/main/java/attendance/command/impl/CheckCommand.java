package attendance.command.impl;

import attendance.command.Command;
import attendance.constant.ErrorMessage;
import attendance.domain.Crew;
import attendance.service.AttendanceService;
import attendance.util.InputParser;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class CheckCommand implements Command {

    private final AttendanceService service;

    public CheckCommand(AttendanceService service) {
        this.service = service;
    }

    @Override
    public void execute(LocalDate now) {
        if (now.getDayOfWeek().equals(DayOfWeek.SATURDAY) || now.getDayOfWeek().equals(DayOfWeek.SUNDAY) || now.isEqual(LocalDate.of(2024,12,25))) {
            throw new IllegalArgumentException(ErrorMessage.NO_ATTENDANCE_DAY.getErrorMessage(now.getMonthValue(), now.getDayOfMonth(), now.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREA)));
        }

        String name = InputView.readName();
        Crew crew = service.getCrew(name, now);

        String rawAttendanceTime = InputView.readAttendanceTime();
        LocalTime attendanceTime = InputParser.parseTime(rawAttendanceTime.trim());
        service.registerAttendance(crew, now, attendanceTime);

        OutputView.print1(LocalDateTime.of(now, attendanceTime), crew.getAttendanceState(now));
    }
}
