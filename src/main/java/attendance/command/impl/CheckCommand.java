package attendance.command.impl;

import attendance.command.Command;
import attendance.service.AttendanceService;
import attendance.util.InputParser;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalTime;

public class CheckCommand implements Command {

    private final AttendanceService service;

    public CheckCommand(AttendanceService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        LocalDate now = DateTimes.now().toLocalDate();
        service.validateHoliday(now);

        String name = InputParser.parseName(InputView.readName());
        service.validateCheckPossible(name, now);

        LocalTime time = InputParser.parseTime(InputView.readTime());
        service.validateOperationTime(time);

        service.check(name, now, time);

        OutputView.printCheckResult(now, time);
    }
}
