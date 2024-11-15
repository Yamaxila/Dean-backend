package by.vstu.migrate.v1.enums;

import by.vstu.dean.enums.EClassroomType;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public enum V1EClassroomType implements V1BaseEnum<V1EClassroomType> {


    LECTURE(0, "лекционная"),

    LAB(1, "лаборатория"),

    SPEC_LAB(2, "спец.лаборатория"),

    COMPUTER_CLASS(3, "комп.класс"),

    PICTURE(4, "рисунок"),
    PICTURE_ART(5, "рисунок/живопись"),
    ART(6, "живопись"),

    EMPTY(7, ""),

    STUDY_CLASS(8, "учебная аудитория"),
    GYM(9, "спортзал"),

    UNKNOWN(-1, "UNKNOWN");

    final int id;
    @Getter
    final String name;


    V1EClassroomType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public EClassroomType map() {
        return switch (this) {
            case LECTURE -> EClassroomType.LECTURE;
            case LAB -> EClassroomType.LAB;
            case SPEC_LAB -> EClassroomType.SPEC_LAB;
            case COMPUTER_CLASS -> EClassroomType.COMPUTER_CLASS;
            case PICTURE -> EClassroomType.PICTURE;
            case PICTURE_ART -> EClassroomType.PICTURE_ART;
            case ART -> EClassroomType.ART;
            case EMPTY -> EClassroomType.EMPTY;
            case STUDY_CLASS -> EClassroomType.STUDY_CLASS;
            case GYM -> EClassroomType.GYM;
            case UNKNOWN -> EClassroomType.UNKNOWN;
        };
    }

    public static V1EClassroomType valueOf(int id) {
        return Arrays.stream(values()).filter(p -> p.id == id).findFirst().orElse(UNKNOWN);
    }

    public static V1EClassroomType byName(String name) {
        return Arrays.stream(V1EClassroomType.values()).filter(p -> p.name.equalsIgnoreCase(name)).findFirst().orElse(UNKNOWN);
    }

    @Override
    public List<V1EClassroomType> getValues() {
        return Arrays.stream(V1EClassroomType.values()).toList();
    }
}
