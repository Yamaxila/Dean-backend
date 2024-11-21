package by.vstu.dean.timetable.enums;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Arrays;

/**
 * Перечисление подгрупп.
 */
@Getter
@Schema(title = "Подгруппа", description = "Перечисление подгрупп")
public enum ESubGroup {

    UNKNOWN(0, ""),

    FIRST(1, "1"),
    SECOND(2, "2"),
    ALL(3, "Все"),
    THIRD(4, "3"),
    FOURTH(5, "4"),
    SEWING(6, "Швейники"),
    SHOE(7, "Обувщики"),
    TEXTILE(8, "Текстильщики"),
    GRAPHIC(9, "Графический дизайн"),
    MULTIMEDIA(10, "Мультимедиа дизайн"),
    WEAVERS(11, "Ткачи"),
    KNITWEAR(12, "Трикотажники");

    final int id;
    final String name;

    ESubGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ESubGroup valueOf(int id) {
        return Arrays.stream(values()).filter(p -> p.id == id).findFirst().orElse(UNKNOWN);
    }
}
