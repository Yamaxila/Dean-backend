package by.vstu.migrate.v1.enums;

import by.vstu.dean.enums.ELessonType;

import java.util.Arrays;
import java.util.List;

public enum V1ELessonType implements V1BaseEnum<V1ELessonType> {

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

    V1ELessonType(int id) {
        this.id = id;
    }

    public ELessonType map() {
        return switch (this) {
            case LECTURE -> ELessonType.LECTURE;
            case PRACTICE -> ELessonType.PRACTICE;
            case LAB -> ELessonType.LAB;
            case EXAM -> ELessonType.EXAM;
            case CONSULTATION -> ELessonType.CONSULTATION;
            case SCORE -> ELessonType.SCORE;
            case EXAM_REVIEW -> ELessonType.EXAM_REVIEW;
            case CONSULT_EXAM -> ELessonType.CONSULT_EXAM;
            case DEFENSE, UNKNOWN -> ELessonType.UNKNOWN;
        };
    }

    public static V1ELessonType valueOf(int id) {
        return Arrays.stream(values()).filter(p -> p.id == id).findFirst().orElse(UNKNOWN);
    }

    @Override
    public List<V1ELessonType> getValues() {
        return Arrays.stream(V1ELessonType.values()).toList();
    }
}
