package attendance.command.impl;

import attendance.command.Command;
import attendance.constant.DangerState;
import attendance.domain.Crew;
import attendance.service.AttendanceService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DangerCommand implements Command {

    private final AttendanceService service;

    public DangerCommand(AttendanceService service) {
        this.service = service;
    }

    @Override
    public void execute(LocalDate now) {
        List<Crew> crews = new ArrayList<>(service.getCrews().getCrews()).stream()
                .sorted().toList();

        System.out.println("\n제적 위험자 조회 결과");
        for (Crew crew : crews) {
            DangerState dangerState = DangerState.from(crew.getAbsenceCount() + crew.getLateCount() / 3);
            if (!dangerState.equals(DangerState.NONE)) {
                System.out.printf("- %s: 결석 %d회, 지각 %d회 (%s)\n", crew.getName(), crew.getAbsenceCount(),
                        crew.getLateCount(), dangerState.getName());
            }
        }
        System.out.println();
    }
}
