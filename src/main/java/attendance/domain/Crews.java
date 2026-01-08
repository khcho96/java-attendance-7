package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Crews {

    private final List<Crew> crews;

    private Crews(List<Crew> crews) {
        this.crews = new ArrayList<>();
    }

    public static Crews from(Map<String, Map<LocalDate, LocalTime>> attendances) {
        List<Crew> crews = new ArrayList<>();
        for (String name : attendances.keySet()) {
            Crew crew = Crew.of(name, attendances.get(name));
            crews.add(crew);
        }
        return new Crews(crews);
    }
}
