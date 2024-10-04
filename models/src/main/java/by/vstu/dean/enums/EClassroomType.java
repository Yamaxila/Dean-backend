package by.vstu.dean.enums;

import by.vstu.dean.core.enums.BaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Schema(title = "Тип аудитории")
public enum EClassroomType implements BaseEnum<EClassroomType> {

    UNKNOWN(0, "UNKNOWN"),

    LECTURE(1, "лекционная"),

    LAB(2, "лаборатория"),

    SPEC_LAB(3, "спец.лаборатория"),

    COMPUTER_CLASS(4, "комп.класс"),

    PICTURE(5, "рисунок"),
    PICTURE_ART(6, "рисунок/живопись"),
    ART(7, "живопись"),

    EMPTY(8, ""),

    STUDY_CLASS(9, "учебная аудитория"),
    GYM(10, "спортзал");

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
