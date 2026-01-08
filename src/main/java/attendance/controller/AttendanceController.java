package attendance.controller;

import attendance.command.MenuCommandRegistry;
import attendance.command.MenuOption;
import attendance.service.AttendanceService;
import attendance.util.InputParser;
import attendance.util.file.FileReader;
import attendance.view.InputView;
import java.io.IOException;
import java.util.List;

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
        service.registerFileInfo(readLines);
    }

    private MenuOption readOption() {
        return InputParser.parseMenu(InputView.readMenuSelection());
    }
}
