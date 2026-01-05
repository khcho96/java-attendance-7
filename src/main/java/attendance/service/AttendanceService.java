package attendance.service;

import attendance.domain.Crews;
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
}
