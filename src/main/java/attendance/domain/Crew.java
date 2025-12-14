package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class Crew {

    private String name;
    private DangerState state;
    private List<Attendance> attendances;
    private int lateCount;
    private int absenceCount;

    public Crew(String name) {
        this.name = name;
        attendances = new ArrayList<>();
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

    public void registerDateAndTime(LocalDate date, LocalTime time) {
        String dayOfWeek = date.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);

        LocalTime lateTime = LocalTime.parse("10:05");
        LocalTime absenceTime = LocalTime.parse("10:30");
        if (dayOfWeek.equals("월")) {
            lateTime = LocalTime.parse("13:05");
            absenceTime = LocalTime.parse("13:30");
        }

        String attendanceState = "출석";
        if (time.isAfter(lateTime)) {
            attendanceState = "지각";
            lateCount++;
        }
        if (time.isAfter(absenceTime)) {
            attendanceState = "결석";
            absenceCount++;
        }

        attendances.add(new Attendance(date, dayOfWeek, time, attendanceState));
    }

    @Override
    public String toString() {
        return "Crew{" +
                "name='" + name + '\'' +
                ", state=" + state +
                ", attendances=" + attendances +
                ", lateCount=" + lateCount +
                ", absenceCount=" + absenceCount +
                '}';
    }

    public String getName() {
        return name;
    }
}
