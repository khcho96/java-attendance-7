package attendance;

import attendance.domain.Attendances;
import attendance.util.file.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Application {

    private static Attendances attendances;

    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader("src/main/resources/attendances.csv");
        List<String> readLines = reader.readLines();
        readLines.removeFirst();
        attendances = Attendances.newInstance();
        for (String readPromotion : readLines) {
            String[] split = readPromotion.split(",");
            String name = split[0];
            String[] split1 = split[1].split(" ");
            String dateFormat = split1[0];
            String timeFormat = split1[1];
            LocalDate date = LocalDate.parse(dateFormat);
            LocalTime time = LocalTime.parse(timeFormat);
            attendances.addAttendance(name, date, time);
        }

        System.out.println(attendances.getCrews());
    }
}
