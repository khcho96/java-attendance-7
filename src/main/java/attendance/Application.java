package attendance;

import attendance.domain.Attendances;
import attendance.domain.Crew;
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

        for (Crew crew : attendances.getCrews()) {
            System.out.println(crew);
        }
        while (true) {
//            LocalDate nowDate = DateTime.now();
            LocalDate nowDate = LocalDate.of(2024, 12, 13);
            String rawChoice = InputView.readChoice(nowDate);
            String choice = InputParser.parseChoice(rawChoice);

            if (choice.equals("Q")) {
                break;
            }

            if (choice.equals("1")) {
                int month = nowDate.getMonthValue();
                int day = nowDate.getDayOfMonth();
                String dayOfWeek = nowDate.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);
                if (dayOfWeek.matches("[토|일]") || nowDate.isEqual(LocalDate.of(2024, 12, 25))) {
                    throw new IllegalArgumentException(
                            String.format("[ERROR] %d월 %d일 %s요일은 등교일이 아닙니다.", month, day, dayOfWeek));
                }

                String name = InputView.readName();
                if (!attendances.contains(name)) {
                    throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
                }

                if (attendances.isAlreadyAttend(name, nowDate)) {
                    throw new IllegalArgumentException("[ERROR] 이미 출석을 확인하였습니다. 필요한 경우 수정 기능을 이용해주세요.");
                }

                String rawTime = InputView.readTime();
                LocalTime newTime = InputParser.parseTime(rawTime);
                LocalTime startTime = LocalTime.of(8, 0, 0);
                LocalTime endTime = LocalTime.of(23, 0, 0);
                if (newTime.isBefore(startTime) || newTime.isAfter(endTime)) {
                    throw new IllegalArgumentException("[ERROR] 캠퍼스 운영 시간에만 출석이 가능합니다.");
                }

                Crew registerdCrew = attendances.registerAttendance(name, newTime, nowDate);
                System.out.println(registerdCrew.getInfoAt(nowDate));

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
