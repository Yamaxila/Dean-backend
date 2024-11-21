package by.vstu.migrate.timetable.enums;

import by.vstu.dean.enums.ELessonType;
import by.vstu.migrate.v1.enums.V1BaseEnum;

import java.util.Arrays;
import java.util.List;

public enum V1ELessonTypes implements V1BaseEnum<V1ELessonTypes> {

    LECTURE(0),
    PRACTICE(1),
    LAB(2),
    EXAM(3),
    CONSULTATION(4),
    SCORE(5),
    COURSE_WORK_DEFENSE(6),
    COURSE_PROJECT_DEFENSE(7),
    EXAM_REVIEW(8),
    CONSULT_EXAM(9),
    DIFF_SCORE(10),
    PRACTICE_DEFENSE(11),
    SEMINAR(12),

    UNKNOWN(-1);

    final int id;

    V1ELessonTypes(int id) {
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
            case COURSE_WORK_DEFENSE -> ELessonType.COURSE_WORK_DEFENSE;
            case COURSE_PROJECT_DEFENSE -> ELessonType.COURSE_PROJECT_DEFENSE;
            case EXAM_REVIEW -> ELessonType.EXAM_REVIEW;
            case CONSULT_EXAM -> ELessonType.CONSULT_EXAM;
            case DIFF_SCORE -> ELessonType.DIFF_SCORE;
            case PRACTICE_DEFENSE -> ELessonType.PRACTICE_DEFENSE;
            case SEMINAR -> ELessonType.SEMINAR;
            case UNKNOWN -> ELessonType.UNKNOWN;
        };
    }

    public static V1ELessonTypes valueOf(int id) {
        return Arrays.stream(values()).filter(p -> p.id == id).findFirst().orElse(UNKNOWN);
    }

    @Override
    public List<V1ELessonTypes> getValues() {
        return Arrays.stream(V1ELessonTypes.values()).toList();
    }
}
