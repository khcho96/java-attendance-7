package attendance.view;

import static java.util.Locale.KOREA;

import attendance.constant.AttendanceState;
import attendance.constant.Danger;
import attendance.domain.Crew;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        System.out.printf("%s (%s)\n", LocalDateTime.of(date, time).format(DATETIME_FMT),
                AttendanceState.of(date, time).getName());
    }

    public static void printModificationResult(LocalDate date, LocalTime time, LocalTime oldTime) {
        if (oldTime.isAfter(LocalTime.of(23, 58))) {
            System.out.printf("%s --:-- (결석) -> %s (%s) 수정 완료!\n", date.format(DATE_FMT),
                    time.format(TIME_FMT), AttendanceState.of(date, time).getName());
            return;
        }
        System.out.printf("%s %s (%s) -> %s (%s) 수정 완료!\n", date.format(DATE_FMT),
                oldTime.format(TIME_FMT), AttendanceState.of(date, oldTime).getName(),
                time.format(TIME_FMT), AttendanceState.of(date, time).getName());
    }

    public static void printRecords(Crew crew) {
        System.out.printf("이번 달 %s의 출석 기록입니다.\n\n", crew.getName());

        Map<LocalDate, LocalTime> attendanceRecords = crew.getAttendanceRecords();
        List<LocalDate> dates = new ArrayList<>(attendanceRecords.keySet());
        dates.sort(null);
        for (LocalDate date : dates) {
            LocalTime time = attendanceRecords.get(date);
            if (time.isAfter(LocalTime.of(23, 58))) {
                System.out.printf("%s --:-- (%s)\n", date.format(DATE_FMT), AttendanceState.of(date, time).getName());
                continue;
            }
            System.out.printf("%s %s (%s)\n", date.format(DATE_FMT), time.format(TIME_FMT),
                    AttendanceState.of(date, time).getName());
        }
        System.out.println();

        System.out.printf("출석: %d회\n", crew.getAttendanceCount());
        System.out.printf("지각: %d회\n", crew.getLateCount());
        System.out.printf("결석: %d회\n\n", crew.getAbsenceCount());

        Danger danger = Danger.from(crew.getAbsenceCount() + crew.getLateCount() / 3);
        if (!danger.equals(Danger.NONE)) {
            System.out.printf("%s 대상자입니다.", danger.getName());
        }
    }
}
