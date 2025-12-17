package attendance;

import attendance.domain.Attendances;
import attendance.domain.Crew;
import attendance.time.DateTime;
import attendance.util.InputParser;
import attendance.util.file.FileReader;
import attendance.view.InputView;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
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

        while (true) {
            LocalDate nowDate = DateTime.now();
//            LocalDate nowDate = LocalDate.of(2024, 12, 13);
            String rawChoice = InputView.readChoice(nowDate);
            String choice = InputParser.parseChoice(rawChoice);

            List<LocalDate> allDates = getAllDates(nowDate);
            List<Crew> allCrews = attendances.getCrews();
            for (Crew crew : allCrews) {
                crew.addAbsenceDate(allDates);
            }

            attendances.setDangerStatus();

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

                String name = InputView.readNameForRegisterAttendance();
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

                attendances.setDangerStatus();

                continue;
            }

            if (choice.equals("2")) {
                String name = InputView.readNameForModifyAttendance();
                if (!attendances.contains(name)) {
                    throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
                }

                String rawModifiedDate = InputView.readDate();
                LocalDate modifiedDate = InputParser.parseDate(rawModifiedDate);
                if (modifiedDate.isAfter(nowDate)) {
                    throw new IllegalArgumentException("[ERROR] 아직 수정할 수 없습니다.");
                }

                int month = modifiedDate.getMonthValue();
                int day = modifiedDate.getDayOfMonth();
                String dayOfWeek = modifiedDate.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREAN);
                if (dayOfWeek.matches("[토|일]") || modifiedDate.isEqual(LocalDate.of(2024, 12, 25))) {
                    throw new IllegalArgumentException(
                            String.format("[ERROR] %d월 %d일 %s요일은 등교일이 아닙니다.", month, day, dayOfWeek));
                }

                String rawTime = InputView.readTime();
                LocalTime newTime = InputParser.parseTime(rawTime);
                LocalTime startTime = LocalTime.of(8, 0, 0);
                LocalTime endTime = LocalTime.of(23, 0, 0);
                if (newTime.isBefore(startTime) || newTime.isAfter(endTime)) {
                    throw new IllegalArgumentException("[ERROR] 캠퍼스 운영 시간에만 출석이 가능합니다.");
                }

                List<Crew> crews = attendances.modifyAttendance(name, newTime, modifiedDate);
                System.out.println(crews.get(0).getInfoAt(modifiedDate) + " -> "
                        + crews.get(1).getInfoAt(modifiedDate) + " 수정 완료!");

                attendances.setDangerStatus();

                continue;
            }

            if (choice.equals("3")) {
                String name = InputView.readNameForModifyAttendance();
                if (!attendances.contains(name)) {
                    throw new IllegalArgumentException("[ERROR] 등록되지 않은 닉네임입니다.");
                }

                System.out.printf("이번 달 %s의 출석 기록입니다.\n", name);
                Crew crew = attendances.getCrew(name);
                List<LocalDate> dates = crew.getDates();
                for (LocalDate date : dates) {
                    System.out.print("\n" + crew.getInfoAt(date));
                }
                System.out.println();

                int attendanceCount = crew.getAttendanceCount();
                int lateCount = crew.getLateCount();
                int absenceCount = crew.getAbsenceCount();
                System.out.println("\n출석: " + attendanceCount + "회");
                System.out.println("지각: " + lateCount + "회");
                System.out.println("결석: " + absenceCount + "회");

                System.out.println("\n" + crew.getDangerState() + " 대상자입니다.");

                continue;
            }

            if (choice.equals("4")) {
                List<Crew> crews = attendances.getCrews();
                System.out.println("제적 위험자 조회 결과");
                for (Crew crew : crews) {
                    System.out.printf("- %s: 결석 %d회, 지각 %d회 (%s)\n", crew.getName(), crew.getAbsenceCount(),
                            crew.getLateCount(), crew.getDangerState());
                }
            }

        }
    }

    private static List<LocalDate> getAllDates(LocalDate nowDate) {
        List<LocalDate> dates = List.of(
                LocalDate.of(2024, 12, 2),
                LocalDate.of(2024, 12, 3),
                LocalDate.of(2024, 12, 4),
                LocalDate.of(2024, 12, 5),
                LocalDate.of(2024, 12, 6),

                LocalDate.of(2024, 12, 9),
                LocalDate.of(2024, 12, 10),
                LocalDate.of(2024, 12, 11),
                LocalDate.of(2024, 12, 12),
                LocalDate.of(2024, 12, 13),

                LocalDate.of(2024, 12, 16),
                LocalDate.of(2024, 12, 17),
                LocalDate.of(2024, 12, 18),
                LocalDate.of(2024, 12, 19),
                LocalDate.of(2024, 12, 20),

                LocalDate.of(2024, 12, 23),
                LocalDate.of(2024, 12, 24),
                LocalDate.of(2024, 12, 26),
                LocalDate.of(2024, 12, 27),

                LocalDate.of(2024, 12, 30),
                LocalDate.of(2024, 12, 31)
        );

        List<LocalDate> resultDate = new ArrayList<>();
        for (LocalDate date : dates) {
            if (date.isBefore(nowDate)) {
                resultDate.add(date);
            }
        }
        return resultDate;
    }
}
