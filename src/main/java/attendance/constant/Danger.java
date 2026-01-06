package attendance.constant;

import java.util.Arrays;

public enum Danger {

    EXPULSION(6, "제적"),
    TALK(3, "면담"),
    WARNING(2, "경고"),
    NONE(0, ""),
    ;

    private final int absenceCount;
    private final String name;

    Danger(int absenceCount, String name) {
        this.absenceCount = absenceCount;
        this.name = name;
    }

    public static Danger from(int count) {
        return Arrays.stream(values())
                .filter(danger -> danger.absenceCount <= count)
                .findFirst()
                .orElse(NONE);
    }

    public String getName() {
        return name;
    }
}
