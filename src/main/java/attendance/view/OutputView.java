package attendance.view;

import static java.util.Locale.KOREA;

import attendance.constant.AttendanceState;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class OutputView {

    private static final DateTimeFormatter DATETIME_FMT =
            DateTimeFormatter.ofPattern("M월 dd일 E요일 HH:mm", KOREA);
    private static final DateTimeFormatter DATE_FMT =
            DateTimeFormatter.ofPattern("M월 dd일 E요일", KOREA);
    private static final DateTimeFormatter TIME_FMT =
            DateTimeFormatter.ofPattern("HH:mm", KOREA);

    private OutputView() {
    }

    public static void printCheckResult(LocalDate date, LocalTime time) {
        System.out.printf("%s (%s)\n", LocalDateTime.of(date,time).format(DATETIME_FMT), AttendanceState.of(date, time).getName());
    }
}
