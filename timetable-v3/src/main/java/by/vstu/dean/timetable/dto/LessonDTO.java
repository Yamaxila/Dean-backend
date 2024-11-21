package by.vstu.dean.timetable.dto;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.dto.v1.lessons.V1DisciplineDTO;
import by.vstu.dean.dto.v1.lessons.V1TeacherDTO;
import by.vstu.dean.dto.v1.rooms.V1ClassroomDTO;
import by.vstu.dean.dto.v1.students.V1GroupDTO;
import by.vstu.dean.enums.ELessonType;
import by.vstu.dean.timetable.enums.ESubGroup;
import by.vstu.dean.timetable.enums.EWeekType;
import by.vstu.dean.timetable.models.LessonModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * DTO for {@link LessonModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(title = "DTO Занятия")
public class LessonDTO extends BaseDTO {

    /**
     * Группа.
     */
    @Schema(title = "Группа")
    private V1GroupDTO group;

    /**
     * Преподаватель.
     */
    @Schema(title = "Преподаватель")
    private V1TeacherDTO teacher;

    /**
     * Дисциплина.
     */
    @Schema(title = "Дисциплина")
    private V1DisciplineDTO discipline;

    /**
     * Аудитория.
     */
    @Schema(title = "Аудитория")
    private V1ClassroomDTO room;

    /**
     * Номер пары (1-7).
     */
    @Schema(title = "Номер пары",
            description = "Номер пары по порядку. 1 - 8:00, 2 - 9:50 и т.д.",
            minimum = "1", maximum = "7",
            allowableValues = {"1", "2", "3", "4", "5", "6", "7"})
    @NotNull
    @ReflectionField(clazz = LessonModel.class)
    private Short lessonNumber;

    /**
     * Номер дня недели (0-6).
     */
    @Schema(title = "Номер дня недели",
            description = "Номер дня недели, начиная с нуля. 0 - пн., 1 - вт., 2 - ср. и т.д.",
            minimum = "0", maximum = "6",
            allowableValues = {"0", "1", "2", "3", "4", "5", "6"})
    @NotNull
    @ReflectionField(clazz = LessonModel.class)
    private Short day;

    /**
     * Тип занятия.
     */
    @Schema(title = "Тип занятия")
    @NotNull
    @ReflectionField(clazz = LessonModel.class)
    private ELessonType lessonType;

    /**
     * Тип недели.
     */
    @Schema(title = "Тип недели")
    @NotNull
    @ReflectionField(clazz = LessonModel.class)
    private EWeekType weekType;

    /**
     * Подгруппа.
     */
    @Schema(title = "Подгруппа")
    @NotNull
    @ReflectionField(clazz = LessonModel.class)
    private ESubGroup subGroup;

    /**
     * Дата начала занятия.
     */
    @Schema(title = "Дата начала занятия")
    @NotNull
    @ReflectionField(clazz = LessonModel.class)
    private LocalDate startDate;

    /**
     * Дата конца занятия.
     */
    @Schema(title = "Дата конца занятия")
    @NotNull
    @ReflectionField(clazz = LessonModel.class)
    private LocalDate endDate;

    /**
     * Флаг видимости занятия в сервисе расписания.
     */
    @Schema(title = "Флаг видимости занятия в сервисе расписания")
    @NotNull
    @ReflectionField(clazz = LessonModel.class)
    private boolean visible;
}
