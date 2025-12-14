package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Attendances {

    private List<Crew> crews;

    public Attendances() {
        crews = new ArrayList<>();
    }

    public static Attendances newInstance() {
        return new Attendances();
    }

    public void addAttendance(String name, LocalDate date, LocalTime time) {
        Crew crew = new Crew(name);
        for (Crew crew1 : crews) {
            if (crew1.getName().equals(name)) {
                crew = crew1;
            }
        }

        crew.registerDateAndTime(date, time);

        if (!crews.contains(crew)) {
            crews.add(crew);
        }
    }

    public boolean contains(String name) {
        Crew crew = new Crew(name);
        return crews.contains(crew);
    }

    public boolean isAlreadyAttend(String name, LocalDate nowDate) {
        Crew checkCrew = new Crew(name);
        for (Crew crew : crews) {
            if (crew.equals(checkCrew) && crew.containsDate(nowDate)) {
                return true;
            }
        }
        return false;
    }
}
