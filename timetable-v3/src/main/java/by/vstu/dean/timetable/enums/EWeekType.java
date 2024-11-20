package by.vstu.dean.timetable.enums;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;

/**
 * Перечисление типов недель.
 */
@Schema(title = "Тип недели")
public enum EWeekType {

    ALWAYS(0),
    NUMERATOR(1),
    DENOMINATOR(2),

    FIRST(11),
    SECOND(12),
    THIRD(13),
    FOURTH(14);

    final int id;

    EWeekType(int id) {
        this.id = id;
    }

    public static EWeekType valueOf(int id) {
        return Arrays.stream(values()).filter(p -> p.id == id).findFirst().orElseThrow();
    }
}
