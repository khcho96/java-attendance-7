package attendance.service;

import attendance.domain.Crews;
import java.time.LocalDateTime;
import java.util.List;

public class DemoService {

    private Crews crews;

    public void registerFileInfo(List<String> name, List<LocalDateTime> localDateTime) {
        crews = Crews.newInstance();

        for (int i = 0; i < name.size(); i++) {
            crews.addCrew(name.get(i), localDateTime.get(i));
        }
    }

    public String executeFeatureA(String string) {
        return null;
    }

    public int[] executeFeatureB(String input) {
        return null;
    }

    public List<String> executeFeatureC(String input) {
        return null;
    }

    public String executeFeatureD(String input) {
        return null;
    }
}
