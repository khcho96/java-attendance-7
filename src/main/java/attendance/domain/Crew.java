package attendance.domain;

import attendance.constant.Check;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    public Check addAttendanceRecord(LocalDateTime dateTime) {
        attendances.add(Attendance.from(dateTime));
        return Check.from(dateTime);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
        Crew crew = (Crew) object;
        return Objects.equals(name, crew.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    public boolean containsDate(LocalDate now) {
        return attendances.stream()
                .anyMatch(attendance -> attendance.getDate().isEqual(now));
    }
}
