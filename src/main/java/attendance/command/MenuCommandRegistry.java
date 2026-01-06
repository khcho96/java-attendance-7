package attendance.command;

import attendance.command.impl.CheckCommand;
import attendance.command.impl.DangerQueryCommand;
import attendance.command.impl.ModificationCommand;
import attendance.command.impl.RecordQueryCommand;
import attendance.service.AttendanceService;
import java.util.EnumMap;

public class MenuCommandRegistry {

    private final EnumMap<MenuOption, Command> commands;

    private MenuCommandRegistry(EnumMap<MenuOption, Command> commands) {
        this.commands = commands;
    }

    public static MenuCommandRegistry from(AttendanceService service) {
        EnumMap<MenuOption, Command> map = new EnumMap<>(MenuOption.class);
        map.put(MenuOption.A, new CheckCommand(service));
        map.put(MenuOption.B, new ModificationCommand(service));
        map.put(MenuOption.C, new RecordQueryCommand(service));
        map.put(MenuOption.D, new DangerQueryCommand(service));
        return new MenuCommandRegistry(map);
    }

    public void execute(MenuOption option) {
        commands.get(option).execute();
    }
}
