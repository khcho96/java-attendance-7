package attendance.domain;

import static java.util.Locale.KOREA;

import attendance.constant.ErrorMessage;
import attendance.constant.Holiday;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Crews {

    private static final DateTimeFormatter DATETIME_FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", KOREA);

    private final List<Crew> crews;

    public Crews() {
        crews = new ArrayList<>();
    }

    public static Crews newInstance() {
        return new Crews();
    }

    public void registerRecord(String crewName, List<String> attendanceRecords) {
        Map<LocalDate, LocalDateTime> dateTimes = new HashMap<>();
        setRecords(attendanceRecords, dateTimes);

        Crew crew = setCrew(crewName, dateTimes);
        crews.add(crew);
    }

    private void setRecords(List<String> attendanceRecords, Map<LocalDate, LocalDateTime> dateTimes) {
        for (String attendanceRecord : attendanceRecords) {
            LocalDateTime localDateTime = LocalDateTime.parse(attendanceRecord, DATETIME_FMT);
            LocalDate localDate = localDateTime.toLocalDate();

            dateTimes.put(localDate, localDateTime);
        }
    }

    private Crew setCrew(String crewName, Map<LocalDate, LocalDateTime> dateTimes) {
        Crew crew = Crew.from(crewName);
        LocalDate now = DateTimes.now().toLocalDate();
        for (LocalDate date = LocalDate.of(2024, 12, 1); date.isBefore(now); date = date.plusDays(1)) {
            if (isHoliDay(date)) {
                continue;
            }

            if (dateTimes.containsKey(date)) { // 해당 날짜 출석 기록이 있으면
                crew.addAttendanceRecord(dateTimes.get(date));
                continue;
            }

            crew.addAttendanceRecord(LocalDateTime.of(date, LocalTime.of(23, 59)));
        }
        return crew;
    }

    private static boolean isHoliDay(LocalDate date) {
        return !Holiday.from(date).equals(Holiday.NONE);
    }

    public Crew getCrew(String findName) {
        Crew findCrew = Crew.from(findName);
        return crews.stream()
                .filter(crew -> crew.equals(findCrew))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.NO_EXIST_NAME_ERROR.getErrorMessage()));
    }
}
