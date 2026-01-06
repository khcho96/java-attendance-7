package attendance.service;

import attendance.constant.Check;
import attendance.constant.ErrorMessage;
import attendance.constant.OperationTime;
import attendance.domain.Crew;
import attendance.domain.Crews;
import attendance.dto.CheckResult;
import attendance.dto.ModificationResult;
import attendance.dto.RecordQueryResult;
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

    public void validateNameExists(String name) {
        crews.getCrew(name);
    }

    public void validateModificationPossibleDate(LocalDate now, LocalDate date) {
        if (date.isAfter(now)) {
            throw new IllegalArgumentException(ErrorMessage.FUTURE_DAY_ERROR.getErrorMessage());
        }
    }

    public ModificationResult modifyRecord(String name, LocalDate date, LocalTime newTime) {
        Crew crew = crews.getCrew(name);
        LocalTime oldTime = crew.modifyRecord(date, newTime);

        return ModificationResult.of(date, oldTime, newTime);
    }

    public RecordQueryResult getAttendanceRecords(String name) {
        Crew crew = crews.getCrew(name);
        return RecordQueryResult.from(crew);
    }

    public List<Crew> getDangers() {
        return crews.getDangers();
    }
}
