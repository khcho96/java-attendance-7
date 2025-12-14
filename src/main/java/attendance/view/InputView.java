package attendance.view;

import static camp.nextstep.edu.missionutils.Console.readLine;

import camp.nextstep.edu.missionutils.Console;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class InputView {

    private static final String CHOICE_REQUEST = "";

    public static String readChoice(LocalDate nowDate) {
        System.out.println(CHOICE_REQUEST);
        int month = nowDate.getMonth().getValue();
        int day = nowDate.getDayOfMonth();
        String dayOfWeek = nowDate.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);
        System.out.printf("오늘은 %d월 %d일 %s요일입니다. 기능을 선택해주세요.\n", month, day, dayOfWeek);
        System.out.println("1. 출석 확인");
        System.out.println("2. 출석 수정");
        System.out.println("3. 크루별 출석 기록 확인");
        System.out.println("4. 제적 위험자 확인");
        System.out.println("Q. 종료");
        return Console.readLine();
    }
}
