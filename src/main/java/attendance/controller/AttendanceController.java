package attendance.controller;

import attendance.command.MenuCommandRegistry;
import attendance.command.MenuOption;
import attendance.service.AttendanceService;
import attendance.util.InputParser;
import attendance.util.file.FileReader;
import attendance.view.InputView;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

        while (true) {
            MenuOption option = readOption();

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

    private MenuOption readOption() {
        String selection = InputView.readMenuSelection();
        return MenuOption.from(selection);
    }
}
