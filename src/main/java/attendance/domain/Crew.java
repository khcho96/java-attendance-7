package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class Crew {

    private final String name;
    private final Attendance attendance;

    private Crew(String name, Attendance attendance) {
        this.name = name;
        this.attendance = attendance;
    }

    public static Crew of(String name, Map<LocalDate, LocalTime> localDateTimes) {
        return new Crew(name, Attendance.from(localDateTimes));
    }

    public String getName() {
        return name;
    }

    public void check(LocalDate date) {
        attendance.check(date);
    }
}
