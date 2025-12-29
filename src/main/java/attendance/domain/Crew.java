package attendance.domain;

import attendance.constant.AttendanceState;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Crew implements Comparable<Crew> {

    private final String name;
    private final Map<LocalDate, LocalTime> dateTimes;
    private final Map<LocalDate, AttendanceState> attendanceStates;

    private Crew(String name) {
        this.name = name;
        this.dateTimes = new HashMap<>();
        this.attendanceStates = new HashMap<>();
    }

    public static Crew of(String name) {
        return new Crew(name);
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

    public AttendanceState getAttendanceState(LocalDate date) {
        return attendanceStates.get(date);
    }

    public void addAttendance(LocalDateTime dateTime) {
        dateTimes.put(dateTime.toLocalDate(), dateTime.toLocalTime());
        attendanceStates.put(dateTime.toLocalDate(), AttendanceState.from(dateTime));
    }

    public boolean isAlreadyAttend(LocalDate now) {
        return dateTimes.containsKey(now);
    }

    public LocalTime getTime(LocalDate localDate) {
        return dateTimes.get(localDate);
    }

    public void modifyAttendance(LocalDateTime dateTime) {
        dateTimes.put(dateTime.toLocalDate(), dateTime.toLocalTime());
        attendanceStates.put(dateTime.toLocalDate(), AttendanceState.from(dateTime));
    }

    public void addEmptyAttendance(LocalDate now) {
        LocalDate startDate = LocalDate.of(2024, 12, 1);

        for (LocalDate date = startDate; date.isBefore(now); date = date.plusDays(1)) {
            if (isHoliday(date)) {
                continue;
            }

            dateTimes.putIfAbsent(date, LocalTime.MIDNIGHT);
            attendanceStates.putIfAbsent(date, AttendanceState.ABSENCE);
        }
    }

    private boolean isHoliday(LocalDate date) {
        return date.getDayOfWeek().equals(DayOfWeek.SATURDAY)
                || date.getDayOfWeek().equals(DayOfWeek.SUNDAY)
                || date.equals(LocalDate.of(2024, 12, 25));
    }

    public int getAttendanceCount() {
        return getCount(AttendanceState.ATTENDANCE);
    }

    public int getLateCount() {
        return getCount(AttendanceState.LATE);
    }

    public int getAbsenceCount() {
        return getCount(AttendanceState.ABSENCE);
    }

    private int getCount(AttendanceState attendance) {
        return (int) attendanceStates.values().stream()
                .filter(attendanceState -> attendanceState.equals(attendance))
                .count();
    }

    @Override
    public int compareTo(Crew crew) {
        Integer thisCount = this.getAbsenceCount() + this.getLateCount() / 3;
        Integer count = crew.getAbsenceCount() + crew.getLateCount() / 3;

        return -1 * thisCount.compareTo(count);
    }
}
