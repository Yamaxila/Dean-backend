package by.vstu.migrate.timetable.enums;


import by.vstu.dean.timetable.enums.ESubGroup;
import lombok.Getter;

import java.util.Arrays;

public enum V1ESubGroup {

    FIRST(0),
    SECOND(1),
    ALL(2),
    THIRD(3),
    FOURTH(4),
    SEWING(5),      // швейники
    SHOE(6),        // обувщики
    TEXTILE(7),     // текстильщики
    GRAPHIC(8),     // графический дизайн
    MULTIMEDIA(9),  // мультимедиа дизайн
    WEAVERS(10),    // ткачи
    KNITWEAR(11),   // трикотажники

    UNKNOWN(-1);

    @Getter
    final int id;

    V1ESubGroup(int id) {
        this.id = id;
    }

    public ESubGroup map() {
        return switch (this) {
            case FIRST -> ESubGroup.FIRST;
            case SECOND -> ESubGroup.SECOND;
            case ALL -> ESubGroup.ALL;
            case THIRD -> ESubGroup.THIRD;
            case FOURTH -> ESubGroup.FOURTH;
            case SEWING -> ESubGroup.SEWING;
            case SHOE -> ESubGroup.SHOE;
            case TEXTILE -> ESubGroup.TEXTILE;
            case GRAPHIC -> ESubGroup.GRAPHIC;
            case MULTIMEDIA -> ESubGroup.MULTIMEDIA;
            case WEAVERS -> ESubGroup.WEAVERS;
            case KNITWEAR -> ESubGroup.KNITWEAR;
            case UNKNOWN -> ESubGroup.UNKNOWN;
        };
    }

    public static V1ESubGroup valueOf(int id) {
        return Arrays.stream(values()).filter(p -> p.id == id).findFirst().orElse(UNKNOWN);
    }
}
