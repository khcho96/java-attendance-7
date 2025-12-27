package attendance;

import attendance.command.MenuCommandRegistry;
import attendance.controller.AttendanceController;
import attendance.service.DemoService;
import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        DemoService service = new DemoService();
        MenuCommandRegistry registry = MenuCommandRegistry.defaultRegistry(service);
        AttendanceController controller = new AttendanceController(registry, service);
        try {
            controller.run();
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
