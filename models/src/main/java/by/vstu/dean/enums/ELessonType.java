package by.vstu.dean.enums;

import by.vstu.dean.core.enums.BaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;
import java.util.List;

@Schema(title = "Тип занятия")
public enum ELessonType implements BaseEnum<ELessonType> {
    UNKNOWN(0),

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
    SEMINAR(12);


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
