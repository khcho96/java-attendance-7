package attendance.domain;

import attendance.constant.AttendanceState;
import attendance.constant.Holiday;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Attendance {

    private final Map<LocalDate, LocalTime> attendances;

    public Attendance(Map<LocalDate, LocalTime> attendances) {
        this.attendances = attendances;
    }

    public static Attendance from(Map<LocalDate, LocalTime> dateTimes) {
        Map<LocalDate, LocalTime> attendances = new HashMap<>();

        LocalDate now = DateTimes.now().toLocalDate();
        for (LocalDate date = LocalDate.of(2024,12,1); date.isBefore(now); date = date.plusDays(1)) {

            if (isHoliDay(date)) {
                continue;
            }

            if (dateTimes.containsKey(date)) {
                attendances.put(date, dateTimes.get(date));
                continue;
            }

            attendances.put(date, LocalTime.of(23,59));
        }

        return new Attendance(attendances);
    }

    private static boolean isHoliDay(LocalDate date) {
        return !Holiday.from(date).equals(Holiday.NONE);
    }

    public boolean contains(LocalDate date) {
        return attendances.containsKey(date);
    }

    public void check(LocalDate date, LocalTime time) {
        attendances.put(date, time);
    }

    public LocalTime modify(LocalDate date, LocalTime newTime) {
        LocalTime oldTime = attendances.get(date);
        attendances.put(date, newTime);
        return oldTime;
    }

    public Map<LocalDate, LocalTime> getRecords() {
        Map<LocalDate, LocalTime> records = new HashMap<>(attendances);
        records.remove(DateTimes.now().toLocalDate());

        return records;
    }

    public int getAttendanceCount() {
        Map<LocalDate, LocalTime> records = getRecords();

        return (int) records.keySet().stream()
                .filter(date -> AttendanceState.of(date, records.get(date)).equals(AttendanceState.ATTENDANCE))
                .count();
    }

    public int getLateCount() {
        Map<LocalDate, LocalTime> records = getRecords();

        return (int) records.keySet().stream()
                .filter(date -> AttendanceState.of(date, records.get(date)).equals(AttendanceState.LATE))
                .count();
    }

    public int getAbsenceCount() {
        Map<LocalDate, LocalTime> records = getRecords();

        return (int) records.keySet().stream()
                .filter(date -> AttendanceState.of(date, records.get(date)).equals(AttendanceState.ABSENCE))
                .count();
    }
}
