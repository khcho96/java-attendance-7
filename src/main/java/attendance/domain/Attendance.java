package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Attendance {

    private LocalDate date;
    private String dayOfWeek;
    private LocalTime time;
    private String state;

    public Attendance(LocalDate date, String dayOfWeek, LocalTime time, String state) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
        this.state = state;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "date=" + date +
                ", dayOfWeek='" + dayOfWeek + '\'' +
                ", time=" + time +
                ", state='" + state + '\'' +
                '}';
    }
}
