package attendance.controller;

import attendance.command.MenuCommandRegistry;
import attendance.command.MenuOption;
import attendance.service.AttendanceService;
import attendance.util.InputParser;
import attendance.util.file.FileReader;
import attendance.view.InputView;
import camp.nextstep.edu.missionutils.DateTimes;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class AttendanceController {

    private final MenuCommandRegistry registry;
    private final AttendanceService service;

    public AttendanceController(MenuCommandRegistry registry, AttendanceService service) {
        this.registry = registry;
        this.service = service;
    }

    public void run() throws IOException {
        registerFileInfo();
        LocalDate now = DateTimes.now().toLocalDate();
//        LocalDate now = LocalDate.of(2024, 12, 13);
        while (true) {
            MenuOption option = readOption(now);

            if (option.equals(MenuOption.QUIT)) {
                return;
            }

            registry.execute(option);
        }
    }

    private void registerFileInfo() throws IOException {
        FileReader fileReader = new FileReader("src/main/resources/attendances.csv");
        List<String> readLines = fileReader.readLines();

        Map<String, List<String>> attendanceRecords = InputParser.getAttendanceRecords(readLines);

        service.registerFileInfo(attendanceRecords);
    }

    private MenuOption readOption(LocalDate now) {
        return InputParser.parseMenu(InputView.readMenuSelection(now));
    }
}
