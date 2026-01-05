package attendance.view;

import attendance.constant.Check;
import attendance.dto.CheckResult;
import attendance.dto.ModificationResult;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
}
