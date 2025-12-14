package attendance;

import attendance.domain.Attendances;
import attendance.time.DateTime;
import attendance.util.InputParser;
import attendance.util.file.FileReader;
import attendance.view.InputView;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

public class Application {

    private static Attendances attendances;

    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader("src/main/resources/attendances.csv");
        List<String> readLines = reader.readLines();
        readLines.removeFirst();
        attendances = Attendances.newInstance();
        for (String readPromotion : readLines) {
            String[] split = readPromotion.split(",");
            String name = split[0];
            String[] split1 = split[1].split(" ");
            String dateFormat = split1[0];
            String timeFormat = split1[1];
            LocalDate date = LocalDate.parse(dateFormat);
            LocalTime time = LocalTime.parse(timeFormat);
            attendances.addAttendance(name, date, time);
        }

//        System.out.println(attendances.getCrews());

        while (true) {
            LocalDate nowDate = DateTime.now();
            String rawChoice = InputView.readChoice(nowDate);
            String choice = InputParser.parseChoice(rawChoice);

            if (choice.equals("Q")) {
                break;
            }

            if (choice.equals("1")) {
                int month = nowDate.getMonthValue();
                int day = nowDate.getDayOfMonth();
                String dayOfWeek = nowDate.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);
                if (dayOfWeek.matches("[토|일]") || nowDate.isEqual(LocalDate.of(24,12,25))) {
                    throw new IllegalArgumentException(String.format("%d월 %d일 %s요일은 등교일이 아닙니다.", month, day, dayOfWeek));
                }



                continue;
            }

            if (choice.equals("2")) {
                continue;
            }

            if (choice.equals("3")) {
                continue;
            }

            if (choice.equals("4")) {

            }

        }
    }
}
