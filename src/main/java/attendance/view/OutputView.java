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

    public static void printModificationResult(LocalDate date, LocalTime time, LocalTime oldTime) {
        if (oldTime.isAfter(LocalTime.of(23,58))) {
            System.out.printf("%s --:-- (결석) -> %s (%s) 수정 완료!\n", date.format(DATE_FMT),
                    time.format(TIME_FMT), AttendanceState.of(date, time).getName());
            return;
        }
        System.out.printf("%s %s (%s) -> %s (%s) 수정 완료!\n", date.format(DATE_FMT),
                oldTime.format(TIME_FMT), AttendanceState.of(date, oldTime).getName(),
                time.format(TIME_FMT), AttendanceState.of(date, time).getName());
    }
}
