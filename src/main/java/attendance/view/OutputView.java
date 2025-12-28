package attendance.view;

import attendance.constant.AttendanceState;
import attendance.constant.DangerState;
import attendance.domain.Crew;
import attendance.view.model.FeatureAModel;
import attendance.view.model.FeatureBModel;
import attendance.view.model.FeatureCModel;
import attendance.view.model.FeatureDModel;
import attendance.view.model.QuitModel;
import attendance.view.model.ViewModel;
import attendance.view.model.ViewModelVisitor;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();
    private static final String _MESSAGE = "";

    public static void printErrorMessage(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    private static final OutputViewVisitor VISITOR = new OutputViewVisitor();

    private OutputView() {
    }

    public static void render(ViewModel model) {
        model.accept(VISITOR);
    }

    private static final class OutputViewVisitor implements ViewModelVisitor {

        @Override
        public void visit(FeatureAModel model) {
            int month = model.dateTime().getMonthValue();
            int day = model.dateTime().getDayOfMonth();
            String dayOfWeek = model.dateTime().getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREA);
            int hour = model.dateTime().getHour();
            int minute = model.dateTime().getMinute();
            String state = model.attendanceState().getName();

            System.out.printf("\n%d월 %02d일 %s요일 %02d:%02d (%s)\n", month, day, dayOfWeek, hour, minute, state);
        }

        @Override
        public void visit(FeatureBModel model) {
            int month = model.newDateTime().getMonthValue();
            int day = model.newDateTime().getDayOfMonth();
            String dayOfWeek = model.newDateTime().getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREA);
            int hour = model.newDateTime().getHour();
            int minute = model.newDateTime().getMinute();
            String state = model.newAttendanceState().getName();

            LocalDateTime oldDateTime = model.oldDateTime();
            int oldHour = oldDateTime.getHour();
            int oldMinute = oldDateTime.getMinute();
            String oldState = AttendanceState.from(oldDateTime).getName();

            System.out.printf("\n%d월 %02d일 %s요일 %02d:%02d (%s) -> %02d:%02d (%s)\n", month, day, dayOfWeek, oldHour,
                    oldMinute, oldState, hour, minute, state);
        }

        @Override
        public void visit(FeatureCModel model) {
            Crew crew = model.crew();

            String name = crew.getName();
            Map<LocalDate, LocalTime> dateTimes = crew.getDateTimes();
            Map<LocalDate, AttendanceState> attendanceStates = crew.getAttendanceStates();
            List<LocalDate> dates = new java.util.ArrayList<>(dateTimes.keySet().stream().toList());
            dates.removeIf(date -> date.isEqual(model.now()));
            dates.sort(Comparator.naturalOrder());

            int attendanceCount = crew.getAttendanceCount(model.now());
            int lateCount = crew.getLateCount(model.now());
            int absenceCount = crew.getAbsenceCount(model.now());

            String dangerState = DangerState.from(absenceCount + lateCount / 3).getName();

            System.out.printf("이번 달 %s의 출석 기록입니다.\n\n", name);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            for (LocalDate date : dates) {
                String time = dateTimes.get(date).format(formatter);
                if (time.equals("00:00")) {
                    time = "--:--";
                }

                String attendanceState = attendanceStates.get(date).getName();
                System.out.printf("%d월 %02d일 %s요일 %s (%s)\n", date.getMonthValue(), date.getDayOfMonth(),
                        date.getDayOfWeek().getDisplayName(TextStyle.NARROW, Locale.KOREA), time, attendanceState);
            }
            System.out.println();

            System.out.println("출석: " + attendanceCount + "회");
            System.out.println("지각: " + lateCount + "회");
            System.out.println("결석: " + absenceCount + "회");
            System.out.println();

            if (!dangerState.isEmpty()) {
                System.out.printf("%s 대상자입니다.\n\n", dangerState);
            }
        }

        @Override
        public void visit(FeatureDModel model) {
            System.out.println(model.title());
            System.out.println(model.message());
        }

        @Override
        public void visit(QuitModel model) {
            // 출력 없음
        }
    }
}
