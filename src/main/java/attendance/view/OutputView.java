package attendance.view;

import attendance.constant.Check;
import attendance.domain.Attendance;
import attendance.domain.Crew;
import attendance.dto.CheckResult;
import attendance.dto.ModificationResult;
import attendance.dto.RecordQueryResult;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
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

    public static void printCheckResult(CheckResult checkResult) {
        Check check = checkResult.check();
        LocalDateTime dateTime = checkResult.dateTime();

        System.out.printf("\n%s (%s)\n", dateTime.format(DATETIME_FMT), check.getName());
    }

    public static void printModificationResult(ModificationResult modificationResult) {
        LocalDate date = modificationResult.date();
        LocalTime oldTime = modificationResult.oldTime();
        LocalTime newTime = modificationResult.newTime();

        System.out.printf("\n%s %s (%s) -> %s (%s) 수정 완료!\n",
                date.format(DATE_FMT),
                oldTime.format(TIME_FMT), Check.from(LocalDateTime.of(date, oldTime)).getName(),
                newTime.format(TIME_FMT), Check.from(LocalDateTime.of(date, newTime)).getName()
        );
    }

    public static void printRecordQuery(RecordQueryResult queryResult) {
        Crew crew = queryResult.crew();
        List<Attendance> attendances = crew.getAttendances();
        attendances.sort(null);

        System.out.println("이번 달 빙티의 출석 기록입니다.\n");
        for (Attendance attendance : attendances) {
            LocalDateTime dateTime = attendance.getDateTime();
            LocalDate date = dateTime.toLocalDate();

            if (!dateTime.toLocalTime().isBefore(LocalTime.of(23, 59))) {
                System.out.printf("%s --:-- (%s)", date.format(DATE_FMT),
                        Check.from(attendance.getDateTime()).getName());
                continue;
            }
            System.out.printf("%s (%s)", dateTime.format(DATETIME_FMT), Check.from(attendance.getDateTime()).getName());
        }
        System.out.println();

        System.out.printf("출석: %d회\n", crew.getAttendanceCount());
        System.out.printf("지각: %d회\n", crew.getLateCount());
        System.out.printf("결석: %d회\n", crew.getAbsenceCount());

        if (crew.isDangerCrew()) {
            System.out.printf("\n%s 대상자입니다.\n", crew.getDangerState());
        }
    }

    public static void printDangers(List<Crew> dangers) {
        System.out.println("\n제적 위험자 조회 결과");
        dangers.sort(Comparator.comparingInt(Crew::getAbsenceLateCount).reversed()
                .thenComparing(Crew::getAbsenceCount).reversed()
                .thenComparing(Crew::getLateCount).reversed()
                .thenComparing(Crew::getName));
        for (Crew danger : dangers) {
            System.out.printf("- %s: 결석 %d회, 지각 %d회 (%s)\n",
                    danger.getName(), danger.getAbsenceCount(), danger.getLateCount(), danger.getDangerState());
        }
        System.out.println();
    }
}
