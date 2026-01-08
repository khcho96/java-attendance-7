package attendance.constant;

import java.util.Arrays;

public enum Danger {

    OUT(6, "제적"),
    INTERVIEW(3, "면담"),
    WARNING(2, "경고"),
    NONE(0, ""),
    ;

    private final int absenceCount;
    private final String name;

    Danger(int absenceCount, String name) {
        this.absenceCount = absenceCount;
        this.name = name;
    }

    public static Danger from(int absenceCount) {
        return Arrays.stream(values())
                .filter(danger -> danger.absenceCount <= absenceCount)
                .findFirst()
                .orElse(NONE);
    }

    public String getName() {
        return name;
    }
}
