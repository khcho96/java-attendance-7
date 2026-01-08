package attendance.domain;

import attendance.constant.ErrorMessage;
import attendance.constant.Holiday;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

public class Attendance {

    private final Map<LocalDate, LocalTime> attendances;

    public Attendance(Map<LocalDate, LocalTime> attendances) {
        this.attendances = new HashMap<>();
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

    public void check(LocalDate date) {
        if (attendances.containsKey(date)) {
            throw new IllegalArgumentException(ErrorMessage.ALREADY_ATTENDANCE_ERROR.getErrorMessage());
        }


    }
}
