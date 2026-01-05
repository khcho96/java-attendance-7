package attendance.util;

import attendance.command.MenuOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class InputParser {

    private static final String DELIMITER = ",";

    private InputParser() {
    }

    public static MenuOption parseMenu(String rawInput) {
        return MenuOption.from(rawInput.strip());
    }

    public static String parseName(String rawInput) {
        return rawInput.strip();
    }

    public static LocalTime parseTime(String rawInput) {
        rawInput = rawInput.strip();

        Validator.validateTimeFormat(rawInput);

        return LocalTime.parse(rawInput);
    }

    public static LocalDate parseDay(String rawInput) {
        rawInput = rawInput.strip();

        Validator.validateDayFormat(rawInput);

        return LocalDate.of(24,12, NumberConvertor.convertToNumber(rawInput));
    }

    public static Map<String, List<String>> getAttendanceRecords(List<String> readLines) {
        readLines.removeFirst();
        Map<String, List<String>> attendanceRecords = new HashMap<>();
        for (String readLine : readLines) {
            String[] split = readLine.split(DELIMITER);
            attendanceRecords.getOrDefault(split[0], new ArrayList<>()).add(split[1]);
        }
        return attendanceRecords;
    }
}
