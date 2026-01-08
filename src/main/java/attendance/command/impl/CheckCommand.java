package attendance.command.impl;

import attendance.command.Command;
import attendance.service.AttendanceService;
import attendance.util.InputParser;
import attendance.view.InputView;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;

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
        service.check(name, now);
    }
}
