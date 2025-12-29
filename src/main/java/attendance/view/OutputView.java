package attendance.view;

import attendance.constant.AttendanceState;
import attendance.constant.DangerState;
import attendance.domain.Crew;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String _MESSAGE = "";

    private OutputView() {
    }

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public static void print1(LocalDateTime localDateTime, AttendanceState attendanceState) {
        int month = localDateTime.getMonthValue();
        int day = localDateTime.getDayOfMonth();
        String dayOfWeek = localDateTime.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREA);
        int hour = localDateTime.getHour();
        int minute = localDateTime.getMinute();
        String state = attendanceState.getName();

        System.out.printf("\n%d월 %02d일 %s요일 %02d:%02d (%s)\n", month, day, dayOfWeek, hour, minute, state);
    }

    public static void print2(LocalDateTime oldDateTime, LocalDateTime dateTime, AttendanceState attendanceState) {
        int month = dateTime.getMonthValue();
        int day = dateTime.getDayOfMonth();
        String dayOfWeek = dateTime.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREA);
        int hour = dateTime.getHour();
        int minute = dateTime.getMinute();
        String state = attendanceState.getName();

        int oldHour = oldDateTime.getHour();
        int oldMinute = oldDateTime.getMinute();
        String oldState = AttendanceState.from(oldDateTime).getName();

        System.out.printf("\n%d월 %02d일 %s요일 %02d:%02d (%s) -> %02d:%02d (%s) 수정 완료!\n", month, day, dayOfWeek, oldHour,
                oldMinute, oldState, hour, minute, state);
    }

    public static void print3(Crew crew, LocalDate now) {
        String name = crew.getName();
        Map<LocalDate, LocalTime> dateTimes = crew.getDateTimes();
        Map<LocalDate, AttendanceState> attendanceStates = crew.getAttendanceStates();
        List<LocalDate> dates = new java.util.ArrayList<>(dateTimes.keySet().stream().toList());

        dates.removeIf(date -> date.isEqual(now));
        Collections.sort(dates); // 날짜순으로 오름차순 정렬
        dates.sort(Collections.reverseOrder()); // 내림차순 정렬

        int attendanceCount = crew.getAttendanceCount();
        int lateCount = crew.getLateCount();
        int absenceCount = crew.getAbsenceCount();

        String dangerState = DangerState.from(absenceCount + lateCount / 3).getName();

        System.out.printf("이번 달 %s의 출석 기록입니다.\n\n", name);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        for (LocalDate date : dates) {
            String time = dateTimes.get(date).format(formatter);
            if (time.equals("00:00")) {
                time = "--:--";
            }

            String attendanceState = attendanceStates.get(date).getName();
            System.out.printf("%d월 %02d일 %s요일 %s (%s)\n", date.getMonthValue(), date.getDayOfMonth(),
                    date.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREA), time, attendanceState);
        }
        System.out.println();

        System.out.println("출석: " + attendanceCount + "회");
        System.out.println("지각: " + lateCount + "회");
        System.out.println("결석: " + absenceCount + "회");
        System.out.println();

        if (!dangerState.isEmpty()) {
            System.out.printf("%s 대상자입니다.\n\n", dangerState);
        }
    }
}
