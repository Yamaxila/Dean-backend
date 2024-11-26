package by.vstu.dean.enums;

import by.vstu.dean.core.enums.BaseEnum;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;
import java.util.List;

@Schema(title = "Тип занятия")
public enum ELessonType implements BaseEnum<ELessonType> {
    UNKNOWN(0, ""),

    LECTURE(1, "Лекция"),
    PRACTICE(2, "Практическая работа"),
    LAB(3, "Лабораторная работа"),
    EXAM(4, "Экзамен"),
    CONSULTATION(5, "Консультация"),
    SCORE(6, "Зачёт"),
    COURSE_WORK_DEFENSE(7, "Защита курсовой работы"),
    COURSE_PROJECT_DEFENSE(8, "Защита курсового проекта"),
    EXAM_REVIEW(9, "Просмотр"),
    CONSULT_EXAM(10, "Консультация. Экзамен"),
    DIFF_SCORE(11, "Дифференцированный зачёт"),
    PRACTICE_DEFENSE(12, "Защита отчета по практике"),
    SEMINAR(13, "Семинар");


    final int id;
    final String name;

    ELessonType(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ELessonType valueOf(int id) {
        return Arrays.stream(values()).filter(p -> p.id == id).findFirst().orElse(UNKNOWN);
    }

    @Override
    public List<ELessonType> getValues() {
        return Arrays.stream(ELessonType.values()).toList();
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
