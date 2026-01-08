package attendance.service;

import static java.util.Locale.KOREA;

import attendance.constant.ErrorMessage;
import attendance.constant.Holiday;
import attendance.domain.Crew;
import attendance.domain.Crews;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttendanceService {

    private static final LocalTime START_TIME = LocalTime.of(8, 0);
    private static final LocalTime END_TIME = LocalTime.of(23, 0);
    private static final DateTimeFormatter DATETIME_FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", KOREA);
    private static final DateTimeFormatter DATE_FMT =
            DateTimeFormatter.ofPattern("M월 dd일 E요일", KOREA);

    private Crews crews;

    public void registerFileInfo(List<String> readLines) {
        Map<String, Map<LocalDate, LocalTime>> attendances = new HashMap<>();

        readLines.removeFirst();
        for (String readLine : readLines) {
            String[] split = readLine.split(",");
            String name = split[0];
            LocalDateTime dateTime = LocalDateTime.parse(split[1], DATETIME_FMT);
            LocalDate date = dateTime.toLocalDate();
            LocalTime time = dateTime.toLocalTime();

            if (attendances.containsKey(name)) {
                attendances.get(name).put(date, time);
                continue;
            }

            attendances.put(name, new HashMap<>());
        }

        crews = Crews.from(attendances);
    }

    public void validateHoliday(LocalDate date) {
        if (isHoliday(date)) {
            throw new IllegalArgumentException(
                    ErrorMessage.NO_ATTENDANCE_DAY_ERROR.getErrorMessage(date.format(DATE_FMT)));
        }
    }

    private static boolean isHoliday(LocalDate date) {
        return !Holiday.from(date).equals(Holiday.NONE);
    }

    public void validateCheckPossible(String name, LocalDate date) {
        Crew crew = crews.getCrew(name);
        crew.validateCheckPossible(date);
    }

    public void check(String name, LocalDate date, LocalTime time) {
        Crew crew = crews.getCrew(name);
        crew.check(date, time);
    }

    public void validateOperationTime(LocalTime time) {
        if (time.isBefore(START_TIME) || time.isAfter(END_TIME)) {
            throw new IllegalArgumentException(ErrorMessage.NO_OPERATION_TIME_ERROR.getErrorMessage());
        }
    }

    public void validateModificationPossible(String name) {
        crews.getCrew(name);
    }

    public void validateModificationPossible(LocalDate date) {
        if (isHoliday(date)) {
            throw new IllegalArgumentException(
                    ErrorMessage.NO_ATTENDANCE_DAY_ERROR.getErrorMessage(date.format(DATE_FMT)));
        }

        if (date.isAfter(DateTimes.now().toLocalDate())) {
            throw new IllegalArgumentException(ErrorMessage.FUTURE_DATE_ERROR.getErrorMessage());
        }
    }
}
