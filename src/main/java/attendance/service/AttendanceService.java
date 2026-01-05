package attendance.service;

import attendance.constant.Check;
import attendance.constant.ErrorMessage;
import attendance.constant.OperationTime;
import attendance.domain.Crew;
import attendance.domain.Crews;
import attendance.dto.CheckResult;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

public class AttendanceService {

    private Crews crews;

    public void registerFileInfo(Map<String, List<String>> attendanceRecords) {
        crews = Crews.newInstance();
        for (String crewName : attendanceRecords.keySet()) {
            crews.registerRecord(crewName, attendanceRecords.get(crewName));
        }
    }

    public void validateCheckPossible(String name, LocalDate now) {
        Crew crew = crews.getCrew(name);
        if (crew.containsDate(now)) {
            throw new IllegalArgumentException(ErrorMessage.ALREADY_ATTENDANCE_ERROR.getErrorMessage());
        }
    }

    public CheckResult checkAttendance(String name, LocalDate now, LocalTime time) {
        Crew crew = crews.getCrew(name);
        Check check = crew.addAttendanceRecord(LocalDateTime.of(now, time));
        return CheckResult.of(LocalDateTime.of(now, time), check);
    }

    public void validatePossibleTime(LocalTime time) {
        if (time.isBefore(OperationTime.START.getTime()) || time.isAfter(OperationTime.END.getTime())) {
            throw new IllegalArgumentException(ErrorMessage.NO_OPERATION_TIME_ERROR.getErrorMessage());
        }
    }
}
