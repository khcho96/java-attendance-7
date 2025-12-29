package attendance.service;

import attendance.domain.Crew;
import attendance.domain.Crews;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class AttendanceService {

    private Crews crews;

    public void registerFileInfo(LocalDate now, List<String> name, List<LocalDateTime> localDateTime) {
        crews = Crews.newInstance();

        for (int i = 0; i < name.size(); i++) {
            crews.addCrew(name.get(i), localDateTime.get(i));
        }

        crews.registerOtherWithoutFile(now);
    }

    public void registerAttendance(Crew crew, LocalDate date, LocalTime time) {
        crew.addAttendance(LocalDateTime.of(date, time));
    }

    public Crew getCrew(String name, LocalDate now) {
        return crews.getCrew(name, now);
    }

    public Crew getCrew(String name) {
        return crews.getCrew(name);
    }

    public LocalDateTime modifyAttendance(Crew crew, LocalDateTime dateTime) {
        LocalDate date = dateTime.toLocalDate();

        LocalDateTime oldDateTime = LocalDateTime.of(date, crew.getTime(date));

        crew.modifyAttendance(dateTime);

        return oldDateTime;
    }

    public Crews getCrews() {
        return crews;
    }
}
