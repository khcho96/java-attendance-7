package attendance.domain;

import attendance.constant.ErrorMessage;
import java.time.LocalDate;
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
        for (Crew crew : crews) {
            if (name.equals(crew.getName())) {
                crew.addAttendance(dateTime);
                return;
            }
        }
        Crew crew = Crew.of(name);
        crew.addAttendance(dateTime);
        crews.add(crew);
    }

    public Crew getCrew(String name, LocalDate now) {
        Crew crew =  crews.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_NAME.getErrorMessage()));
        if (crew.isAlreadyAttend(now)) {
            throw new IllegalArgumentException(ErrorMessage.ALREADY_ATTEND.getErrorMessage());
        }
        return crew;
    }

    public Crew getCrew(String name) {
        return crews.stream()
                .filter(c -> c.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.INVALID_NAME.getErrorMessage()));
    }

    public void registerOtherWithoutFile(LocalDate now) {
        for (Crew crew : crews) {
            crew.addEmptyAttendance(now);
        }
    }
}
