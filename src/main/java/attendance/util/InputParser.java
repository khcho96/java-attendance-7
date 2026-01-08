package attendance.util;

import static java.util.Locale.KOREA;

import attendance.command.MenuOption;
import camp.nextstep.edu.missionutils.DateTimes;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class InputParser {

    private static final DateTimeFormatter TIME_FMT =
            DateTimeFormatter.ofPattern("HH:mm", KOREA);

    private InputParser() {
    }

    public static String parseName(String rawInput) {
        return rawInput.strip();
    }

    public static LocalDate parseDate(String rawInput) {
        rawInput = rawInput.strip();

        Validator.validateDayFormat(rawInput);

        int day = NumberConvertor.convertToNumber(rawInput);

        LocalDate now = DateTimes.now().toLocalDate();
        return LocalDate.of(now.getYear(), now.getMonthValue(), day);
    }

    public static LocalTime parseTime(String rawInput) {
        rawInput = rawInput.strip();

        Validator.validateTimeFormat(rawInput);

        return LocalTime.parse(rawInput, TIME_FMT);
    }

    public static MenuOption parseMenu(String rawInput) {
        return MenuOption.from(rawInput.strip());
    }
}
