package by.vstu.dean.timetable.enums;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;

/**
 * Перечисление типов недель.
 */
@Schema(title = "Тип недели")
public enum EWeekType {

    ALWAYS(0, "Всегда"),
    NUMERATOR(1, "Числитель"),
    DENOMINATOR(2, "Знаменатель"),

    FIRST(11, "1"),
    SECOND(12, "2"),
    THIRD(13, "3"),
    FOURTH(14, "4");

    final int id;
    final String name;

    EWeekType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static EWeekType valueOf(int id) {
        return Arrays.stream(values()).filter(p -> p.id == id).findFirst().orElseThrow();
    }
}
