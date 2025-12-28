package attendance.command;

import attendance.command.impl.FeatureACommand;
import attendance.command.impl.FeatureBCommand;
import attendance.command.impl.FeatureCCommand;
import attendance.command.impl.QuitCommand;
import attendance.service.DemoService;
import java.time.LocalDate;
import java.util.EnumMap;

public class MenuCommandRegistry {

    private final EnumMap<MenuOption, Command<? extends Command<?>>> commands;

    private MenuCommandRegistry(EnumMap<MenuOption, Command<? extends Command<?>>> commands) {
        this.commands = commands;
    }

    public static MenuCommandRegistry defaultRegistry(DemoService service) {
        EnumMap<MenuOption, Command<? extends Command<?>>> map = new EnumMap<>(MenuOption.class);
        map.put(MenuOption.A, new FeatureACommand(service));
        map.put(MenuOption.B, new FeatureBCommand(service));
        map.put(MenuOption.C, new FeatureCCommand(service));
//        map.put(MenuOption.D, new FeatureDCommand(service));
        map.put(MenuOption.QUIT, new QuitCommand());
        return new MenuCommandRegistry(map);
    }

    public CommandResponse execute(MenuOption option, LocalDate now) {
        return commands.get(option).execute(now);
    }
}
