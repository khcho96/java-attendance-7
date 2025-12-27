package attendance.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Crews {

    private final List<Crew> crews;

    private Crews() {
        crews = new ArrayList<>();
    }

    public static Crews newInstance() {
        return new Crews();
    }

    public void addCrew(String name, LocalDateTime dateTime) {
        Crew crew = Crew.getInstance(name);
        crew.addAttendance(dateTime);
        crews.add(crew);
    }
}
