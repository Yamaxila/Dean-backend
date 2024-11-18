package by.vstu.dean.timetable.enums;


import lombok.Getter;

import java.util.Arrays;

@Getter
public enum ESubGroup {

    UNKNOWN(0),

    FIRST(1),
    SECOND(2),
    ALL(3),
    THIRD(4),
    FOURTH(5),
    SEWING(6),         // швейники
    SHOE(7),           // обувщики
    TEXTILE(8),        // текстильщики
    GRAPHIC(9),        // графический дизайн
    MULTIMEDIA(10),    // мультимедиа дизайн
    WEAVERS(11),       // ткачи
    KNITWEAR(12);      // трикотажники;

    final int id;

    ESubGroup(int id) {
        this.id = id;
    }

    public static ESubGroup valueOf(int id) {
        return Arrays.stream(values()).filter(p -> p.id == id).findFirst().orElse(UNKNOWN);
    }
}
