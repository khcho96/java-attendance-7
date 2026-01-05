package attendance.command.impl;

import static java.util.Locale.KOREA;

import attendance.command.Command;
import attendance.constant.ErrorMessage;
import attendance.constant.Holiday;
import attendance.dto.CheckResult;
import attendance.service.AttendanceService;
import attendance.util.InputParser;
import attendance.view.InputView;
import attendance.view.OutputView;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CheckCommand implements Command {

    private static final DateTimeFormatter DATETIME_FMT =
            DateTimeFormatter.ofPattern("MM월 dd일 E요일", KOREA);

    private final AttendanceService service;

    public CheckCommand(AttendanceService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        LocalDate now = DateTimes.now().toLocalDate();
//        LocalDate now = LocalDate.of(2024, 12, 13);
        if (isHoliday(now)) {
            throw new IllegalArgumentException(
                    ErrorMessage.NO_ATTENDANCE_DAY_ERROR.getErrorMessage(now.format(DATETIME_FMT)));
        }

        String name = InputParser.parseName(InputView.readName());
        service.validateCheckPossible(name, now);

        LocalTime time = InputParser.parseTime(InputView.readTime());
        service.validatePossibleTime(time);

        CheckResult checkResult = service.checkAttendance(name, now, time);

        OutputView.printCheckResult(checkResult);
    }

    private boolean isHoliday(LocalDate now) {
        return !Holiday.from(now).equals(Holiday.NONE);
    }
}
