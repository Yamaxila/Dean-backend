package by.vstu.dean.enums;

import by.vstu.dean.core.enums.BaseEnum;

import java.util.Arrays;
import java.util.List;

public enum ELessonType implements BaseEnum<ELessonType> {

    LECTURE(0),
    PRACTICE(1),
    LAB(2),
    EXAM(3),
    CONSULTATION(4),
    SCORE(5),
    DEFENSE(6),
    EXAM_REVIEW(7),
    CONSULT_EXAM(8),

    UNKNOWN(-1);

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
