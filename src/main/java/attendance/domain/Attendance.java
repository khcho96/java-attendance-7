package attendance.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class Attendance implements Comparable<Attendance> {

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

    public void modify(LocalDate modifiedDate, String dayOfWeek, LocalTime newTime, String attendanceState) {
        this.date = modifiedDate;
        this.dayOfWeek = dayOfWeek;
        this.time = newTime;
        this.state = attendanceState;
    }

    public Attendance clone() {
        return new Attendance(this.date, this.dayOfWeek, this.time, this.state);
    }

    @Override
    public int compareTo(Attendance o) {
        if (this.date.isBefore(o.date)) {
            return -1;
        }

        if (this.date.isAfter(o.date)) {
            return 1;
        }

        return 0;
    }
}
