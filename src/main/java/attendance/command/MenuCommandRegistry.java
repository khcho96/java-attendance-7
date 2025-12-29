package attendance.command;

import attendance.command.impl.CheckCommand;
import attendance.command.impl.ModificationCommand;
import attendance.command.impl.HistoryCommand;
import attendance.command.impl.DangerCommand;
import attendance.service.AttendanceService;
import java.time.LocalDate;
import java.util.EnumMap;

public class MenuCommandRegistry {

    private final EnumMap<MenuOption, Command> commands;

    private MenuCommandRegistry(EnumMap<MenuOption, Command> commands) {
        this.commands = commands;
    }

    public static MenuCommandRegistry defaultRegistry(AttendanceService service) {
        EnumMap<MenuOption, Command> map = new EnumMap<>(MenuOption.class);
        map.put(MenuOption.A, new CheckCommand(service));
        map.put(MenuOption.B, new ModificationCommand(service));
        map.put(MenuOption.C, new HistoryCommand(service));
        map.put(MenuOption.D, new DangerCommand(service));
        return new MenuCommandRegistry(map);
    }

    public void execute(MenuOption option, LocalDate now) {
        commands.get(option).execute(now);
    }
}
