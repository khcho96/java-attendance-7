package attendance.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Crew {

    private final String name;
    private final List<Attendance> attendances;

    public Crew(String name) {
        this.name = name;
        this.attendances = new ArrayList<>();
    }

    public static Crew from(String crewName) {
        return new Crew(crewName);
    }

    public void addAttendanceRecord(LocalDateTime dateTime) {
        attendances.add(Attendance.from(dateTime));
    }
}
