package by.vstu.dean.enums;

import by.vstu.dean.core.enums.BaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Schema(title = "Тип аудитории")
public enum EClassroomType implements BaseEnum<EClassroomType> {


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


    EClassroomType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static EClassroomType valueOf(int id) {
        return Arrays.stream(values()).filter(p -> p.id == id).findFirst().orElse(UNKNOWN);
    }

    public static EClassroomType byName(String name) {
        return Arrays.stream(EClassroomType.values()).filter(p -> p.name.equalsIgnoreCase(name)).findFirst().orElse(UNKNOWN);
    }

    @Override
    public List<EClassroomType> getValues() {
        return Arrays.stream(EClassroomType.values()).toList();
    }
}
