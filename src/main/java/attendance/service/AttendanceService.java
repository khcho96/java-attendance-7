package attendance.service;

import static java.util.Locale.KOREA;

import attendance.domain.Crews;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceService {

    private static final DateTimeFormatter DATETIME_FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", KOREA);

    private Crews crews;

    public void registerFileInfo(List<String> readLines) {
        Map<String, Map<LocalDate, LocalTime>> attendances = new HashMap<>();

        readLines.removeFirst();
        for (String readLine : readLines) {
            String[] split = readLine.split(",");
            String name = split[0];
            LocalDateTime dateTime = LocalDateTime.parse(split[1], DATETIME_FMT);
            LocalDate date = dateTime.toLocalDate();
            LocalTime time = dateTime.toLocalTime();

            if (attendances.containsKey(name)) {
                attendances.get(name).put(date, time);
                continue;
            }

            attendances.put(name, new HashMap<>());
        }

        crews = Crews.from(attendances);
    }
}
