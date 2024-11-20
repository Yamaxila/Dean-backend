package by.vstu.dean.timetable.enums;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Arrays;

/**
 * Перечисление подгрупп.
 */
@Getter
@Schema(title = "Подгруппа")
public enum ESubGroup {

    UNKNOWN(0, ""),

    FIRST(1, "1"),
    SECOND(2, "2"),
    ALL(3, "Все"),
    THIRD(4, "3"),
    FOURTH(5, "4"),
    SEWING(6, "Швейники"),         // швейники
    SHOE(7, "Обувщики"),           // обувщики
    TEXTILE(8, "Текстильщики"),        // текстильщики
    GRAPHIC(9, "Графический дизайн"),        // графический дизайн
    MULTIMEDIA(10, "Мультимедиа дизайн"),    // мультимедиа дизайн
    WEAVERS(11, "Ткачи"),       // ткачи
    KNITWEAR(12, "Трикотажники");      // трикотажники;

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
