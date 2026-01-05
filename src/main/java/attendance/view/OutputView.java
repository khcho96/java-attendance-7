package attendance.view;

import attendance.constant.Check;
import attendance.dto.CheckResult;
import java.time.LocalDateTime;
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

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public static void printCheckResult(CheckResult checkResult) {
        Check check = checkResult.check();
        LocalDateTime dateTime = checkResult.dateTime();

        System.out.printf("\n%s (%s)\n", dateTime.format(DATETIME_FMT), check.getName());
    }
}
