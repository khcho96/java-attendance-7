package attendance.view;

import static java.util.Locale.KOREA;

import camp.nextstep.edu.missionutils.Console;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InputView {

    private static final DateTimeFormatter DATETIME_FMT =
            DateTimeFormatter.ofPattern("MM월 dd일 E요일", KOREA);

    public static String readMenuSelection(LocalDate now) {
        System.out.printf("\n오늘은 %s입니다. 기능을 선택해 주세요.\n"
                        + "1. 출석 확인\n"
                        + "2. 출석 수정\n"
                        + "3. 크루별 출석 기록 확인\n"
                        + "4. 제적 위험자 확인\n"
                        + "Q. 종료\n", now.format(DATETIME_FMT));
        return Console.readLine();
    }

    public static String readName() {
        System.out.println("\n닉네임을 입력해 주세요.");
        return Console.readLine();
    }

    public static String readTime() {
        System.out.println("등교 시간을 입력해 주세요.");
        return Console.readLine();
    }

    public static String readModificationName() {
        System.out.println("\n닉네임을 입력해 주세요.");
        return Console.readLine();
    }

    public static String readModificationDay() {
        System.out.println("수정하려는 날짜(일)를 입력해 주세요.");
        return Console.readLine();
    }

    public static String readModificationTime() {
        System.out.println("언제로 변경하겠습니까?");
        return Console.readLine();
    }
}
