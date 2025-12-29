package attendance.view;

import attendance.constant.AttendanceState;
import attendance.constant.DangerState;
import attendance.domain.Crew;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class OutputView {

    private static final Locale KOREA = Locale.KOREA;
    private static final DateTimeFormatter DATETIME_FMT =
            DateTimeFormatter.ofPattern("M월 dd일 E요일 HH:mm", KOREA);
    private static final DateTimeFormatter DATE_FMT =
            DateTimeFormatter.ofPattern("M월 dd일 E요일", KOREA);
    private static final DateTimeFormatter TIME_FMT =
            DateTimeFormatter.ofPattern("HH:mm", KOREA);

    private OutputView() {
    }

    public static void print1(LocalDateTime localDateTime, AttendanceState attendanceState) {
        System.out.printf("\n%s (%s)\n", localDateTime.format(DATETIME_FMT), attendanceState.getName());
    }

    public static void print2(LocalDateTime oldDateTime, LocalDateTime dateTime, AttendanceState attendanceState) {
        LocalTime time = dateTime.toLocalTime();

        System.out.printf("\n%s (%s) -> %s (%s) 수정 완료!\n",
                oldDateTime.format(DATETIME_FMT), AttendanceState.from(oldDateTime).getName(),
                time.format(TIME_FMT), attendanceState.getName()
                );
    }

    public static void print3(Crew crew, LocalDate now) {
        String name = crew.getName();
        List<LocalDate> dates = new ArrayList<>(crew.getDateTimes().keySet().stream()
                .sorted()
                .toList());
        dates.removeIf(date -> date.isEqual(now));

        System.out.printf("이번 달 %s의 출석 기록입니다.\n\n", name);

        for (LocalDate date : dates) {
            System.out.println(formatAttendanceLine(
                    date,
                    crew.getDateTimes().get(date),
                    crew.getAttendanceStates().get(date)
            ));
        }
        System.out.println();

        printCount(crew);

        String dangerState = DangerState.from(crew.getAbsenceCount() + crew.getLateCount() / 3).getName();
        if (!dangerState.isEmpty()) {
            System.out.printf("%s 대상자입니다.\n\n", dangerState);
        }
    }

    private static String formatAttendanceLine(LocalDate date, LocalTime time, AttendanceState attendanceState) {
        String timeStr = formatTimeOrPlaceholder(time);
        return String.format("%s %s (%s)", date.format(DATE_FMT), timeStr, attendanceState.getName());
    }

    private static String formatTimeOrPlaceholder(LocalTime time) {
        if (LocalTime.MIDNIGHT.equals(time)) {
            return "--:--";
        }
        return time.format(TIME_FMT);
    }

    private static void printCount(Crew crew) {
        System.out.println("출석: " + crew.getAttendanceCount() + "회");
        System.out.println("지각: " + crew.getLateCount() + "회");
        System.out.println("결석: " + crew.getAbsenceCount() + "회");
        System.out.println();
    }
}
