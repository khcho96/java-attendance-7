package attendance.view;

import static java.util.Locale.KOREA;

import camp.nextstep.edu.missionutils.Console;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InputView {

    private static final DateTimeFormatter DATETIME_FMT =
            DateTimeFormatter.ofPattern("MM월 dd일 E요일", KOREA);

    public static String readMenuSelection(LocalDate now) {
        System.out.printf("오늘은 %s입니다. 기능을 선택해 주세요.\n", now.format(DATETIME_FMT));
        return Console.readLine();
    }
}
