package attendance.domain;

import static java.util.Locale.KOREA;

import camp.nextstep.edu.missionutils.DateTimes;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Crews {

    private static final DateTimeFormatter DATETIME_FMT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm", KOREA);

    private List<Crew> crews;

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
        for (LocalDate date = LocalDate.of(24,12,1); date.isBefore(now) ; date.plusDays(1)) {

            if (date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY || date.isEqual(
                    LocalDate.of(24,12,25))) {
                continue;
            }

            if (dateTimes.containsKey(date)) { // 해당 날짜 출석 기록이 있으면
                crew.addAttendanceRecord(dateTimes.get(date));
                continue;
            }

            crew.addAttendanceRecord(LocalDateTime.of(date, LocalTime.of(23,59)));
        }
        return crew;
    }
}
