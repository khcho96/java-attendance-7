package attendance.domain;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

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

    public Crew registerAttendance(String name, LocalTime newTime, LocalDate nowDate) {
        Crew checkCrew = new Crew(name);
        for (Crew crew : crews) {
            if (crew.equals(checkCrew)) {
                return crew.registerAttendance(newTime, nowDate);
            }
        }
        return null;
    }

    public List<Crew> getCrews() {
        Collections.sort(crews);
        return crews;
    }

    public Crew getCrew(String name) {
        for (Crew crew : crews) {
            if (crew.equals(new Crew(name))) {
                return crew;
            }
        }
        return null;
    }

    public List<Crew> modifyAttendance(String name, LocalTime newTime, LocalDate modifiedDate) {
        List<Crew> crews = new ArrayList<>();
        Crew checkCrew = new Crew(name);
        for (Crew crew : this.crews) {
            if (crew.equals(checkCrew)) {
                crews.add(crew.clone());
                crew.modifyAttendance(newTime, modifiedDate);
                crews.add(crew);
                return crews;
            }
        }
        return null;
    }

    public void setDangerStatus() {
        for (Crew crew : crews) {
            crew.setDangerStatus();
        }
    }
}
