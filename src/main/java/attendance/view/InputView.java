package attendance.view;

import camp.nextstep.edu.missionutils.Console;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class InputView {

    private static final String SELECTION_REQUEST = "오늘은 %d월 %d일 %s요일입니다. 기능을 선택해주세요.\n"
            + "1. 출석 확인\n"
            + "2. 출석 수정\n"
            + "3. 크루별 출석 기록 확인\n"
            + "4. 제적 위험자 확인\n"
            + "Q. 종료\n";

    public static String readMenuSelection(LocalDate now) {
        System.out.printf(SELECTION_REQUEST,
                now.getMonthValue(), now.getDayOfMonth(), now.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREA));
        return Console.readLine();
    }

    public static String readName() {
        System.out.println("닉네임을 입력해 주세요.");
        return Console.readLine();
    }

    public static String readAttendanceTime() {
        System.out.println("등교 시간을 입력해 주세요.");
        return Console.readLine();
    }

    public static String readNameForModification() {
        System.out.println("출석을 수정하려는 크루의 닉네임을 입력해 주세요.");
        return Console.readLine();
    }

    public static String readDayOfMonth() {
        System.out.println("수정하려는 날짜(일)를 입력해 주세요.");
        return Console.readLine();
    }

    public static String readModificationTime() {
        System.out.println("언제로 변경하시겠습니까?");
        return Console.readLine();
    }
}
