package attendance.domain;

import attendance.constant.ErrorMessage;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Crews {

    private final List<Crew> crews;

    private Crews(List<Crew> crews) {
        this.crews = crews;
    }

    public static Crews from(Map<String, Map<LocalDate, LocalTime>> attendances) {
        List<Crew> crews = new ArrayList<>();
        for (String name : attendances.keySet()) {
            Crew crew = Crew.of(name, attendances.get(name));
            crews.add(crew);
        }
        return new Crews(crews);
    }

    public Crew getCrew(String name) {
        return crews.stream()
                .filter(crew -> crew.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.NO_EXIST_NAME_ERROR.getErrorMessage()));
    }
}
