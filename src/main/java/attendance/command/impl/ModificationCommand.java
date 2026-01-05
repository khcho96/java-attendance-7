package attendance.command.impl;

import attendance.command.Command;
import attendance.dto.ModificationResult;
import attendance.service.AttendanceService;
import attendance.util.InputParser;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.time.LocalDate;
import java.time.LocalTime;

public class ModificationCommand implements Command {

    private final AttendanceService service;

    public ModificationCommand(AttendanceService service) {
        this.service = service;
    }

    @Override
    public void execute() {
        String name = InputParser.parseName(InputView.readModificationName());
        service.validateNameExists(name);

        LocalDate date = InputParser.parseDay(InputView.readModificationDay());
//        LocalDate now = DateTimes.now().toLocalDate();
        LocalDate now = LocalDate.of(2024, 12, 13);
        service.validateModificationPossibleDate(now, date);

        LocalTime newTime = InputParser.parseTime(InputView.readModificationTime());
        service.validatePossibleTime(newTime);

        ModificationResult modificationResult = service.modifyRecord(name, date, newTime);

        OutputView.printModificationResult(modificationResult);
    }
}
