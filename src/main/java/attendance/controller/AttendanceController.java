package attendance.controller;

import attendance.command.CommandResponse;
import attendance.command.Flow;
import attendance.command.MenuCommandRegistry;
import attendance.command.MenuOption;
import attendance.service.DemoService;
import attendance.util.file.FileReader;
import attendance.view.InputView;
import attendance.view.OutputView;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AttendanceController {

    private final MenuCommandRegistry registry;
    private final DemoService service;

    public AttendanceController(MenuCommandRegistry registry, DemoService service) {
        this.registry = registry;
        this.service = service;
    }

    public void run() throws IOException {

//        LocalDate now = DateTimes.now().toLocalDate();
        LocalDate now = LocalDate.of(2024,12,13);
        registerFileInfo(now);

        while (true) {
            String selection = InputView.readMenuSelection(now);
            MenuOption option = MenuOption.from(selection);
            CommandResponse response = registry.execute(option, now);

            if (response.flow() == Flow.EXIT) {
                return;
            }

            OutputView.render(response.model());
        }
    }

    private void registerFileInfo(LocalDate now) throws IOException {
        FileReader fileReader = new FileReader("src/main/resources/attendances.csv");
        List<String> lines = fileReader.readLines();
        List<String> names = new ArrayList<>();
        List<LocalDateTime> dateTimes = new ArrayList<>();

        lines.removeFirst();
        for (String line : lines) {
            String[] split = line.split(",");
            String name = split[0];
            names.add(name);

            String[] dt = split[1].split(" ");
            String dateFormat = dt[0];
            String timeFormat = dt[1];
            LocalDateTime localDateTime = LocalDateTime.of(LocalDate.parse(dateFormat), LocalTime.parse(timeFormat));
            dateTimes.add(localDateTime);
        }
        service.registerFileInfo(now, names, dateTimes);
    }
}
