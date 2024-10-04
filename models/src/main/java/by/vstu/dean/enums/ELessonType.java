package by.vstu.dean.enums;

import by.vstu.dean.core.enums.BaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;
import java.util.List;

@Schema(title = "Тип занятия")
public enum ELessonType implements BaseEnum<ELessonType> {
    UNKNOWN(0),

    LECTURE(1),
    PRACTICE(2),
    LAB(3),
    EXAM(4),
    CONSULTATION(5),
    SCORE(6),
    DEFENSE(7),
    EXAM_REVIEW(8),
    CONSULT_EXAM(9);


    final int id;

    ELessonType(int id) {
        this.id = id;
    }

    public static ELessonType valueOf(int id) {
        return Arrays.stream(values()).filter(p -> p.id == id).findFirst().orElse(UNKNOWN);
    }

    @Override
    public List<ELessonType> getValues() {
        return Arrays.stream(ELessonType.values()).toList();
    }
}
