package by.vstu.migrate.v1.timetable.enums;

import by.vstu.dean.timetable.enums.EWeekType;

import java.util.Arrays;

public enum V1EWeekType {

    ALWAYS(0),
    NUMERATOR(1),
    DENOMINATOR(2),

    FIRST(11),
    SECOND(12),
    THIRD(13),
    FOURTH(14);

    final int id;

    V1EWeekType(int id) {
        this.id = id;
    }

    public EWeekType map() {
        return switch (this) {
            case ALWAYS -> EWeekType.ALWAYS;
            case NUMERATOR -> EWeekType.NUMERATOR;
            case DENOMINATOR -> EWeekType.DENOMINATOR;
            case FIRST -> EWeekType.FIRST;
            case SECOND -> EWeekType.SECOND;
            case THIRD -> EWeekType.THIRD;
            case FOURTH -> EWeekType.FOURTH;
        };
    }

    public static V1EWeekType valueOf(int id) {
        return Arrays.stream(values()).filter(p -> p.id == id).findFirst().orElseThrow();
    }
}
