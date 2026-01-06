package attendance;

import attendance.command.MenuCommandRegistry;
import attendance.controller.AttendanceController;
import attendance.service.AttendanceService;
import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        AttendanceService service = new AttendanceService();
        MenuCommandRegistry registry = MenuCommandRegistry.from(service);
        AttendanceController controller = new AttendanceController(registry, service);
        try {
            controller.run();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
