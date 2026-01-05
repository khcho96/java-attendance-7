package attendance.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Attendance implements Comparable<Attendance> {

    private LocalDateTime dateTime;

    private Attendance(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public static Attendance from(LocalDateTime dateTime) {
        return new Attendance(dateTime);
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public LocalTime modifyTime(LocalTime newTime) {
        LocalTime oldTime = dateTime.toLocalTime();
        dateTime = LocalDateTime.of(dateTime.toLocalDate(), newTime);
        return oldTime;
    }

    @Override
    public int compareTo(Attendance o) {
        if (dateTime.toLocalDate().isBefore(o.dateTime.toLocalDate())){
            return -1;
        }

        if (dateTime.toLocalDate().isEqual(o.dateTime.toLocalDate())){
            return 0;
        }

        return 1;
    }
}
