package attendance.domain;

import attendance.constant.AttendanceState;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Crew {

    private static Crew CREW;

    private final String name;
    private final Map<LocalDate, LocalTime> dateTimes;
    private final Map<LocalDate, AttendanceState> attendanceStates;

    private Crew(String name) {
        this.name = name;
        this.dateTimes = new HashMap<>();
        this.attendanceStates = new HashMap<>();
    }

    public static Crew getInstance(String name) {
        if (CREW == null) {
            CREW = new Crew(name);
        }
        return CREW;
    }

    public String getName() {
        return name;
    }

    public Map<LocalDate, LocalTime> getDateTimes() {
        return dateTimes;
    }

    public Map<LocalDate, AttendanceState> getAttendanceStates() {
        return attendanceStates;
    }

    public void addAttendance(LocalDateTime dateTime) {
        dateTimes.put(dateTime.toLocalDate(), dateTime.toLocalTime());
        attendanceStates.put(dateTime.toLocalDate(), AttendanceState.from(dateTime));
    }
}
