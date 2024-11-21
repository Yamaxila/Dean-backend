package by.vstu.dean.schedule.dtos;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.PublicDTO;
import by.vstu.dean.dto.v1.pub.teachers.V1PublicTeacherDTO;
import by.vstu.dean.dto.v1.students.V1GroupDTO;
import by.vstu.dean.timetable.models.LessonModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Расписание.
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "Расписание")
public class ScheduleDTO extends PublicDTO {

    /**
     * Заочники: true/false.
     */
    @Schema(title = "Заочники: true/false")
    private boolean correspondence;

    /**
     * Группы.
     */
    @Schema(title = "Группы")
    private V1GroupDTO group;

    /**
     * Преподаватель.
     */
    @Schema(title = "Преподаватель")
    private V1PublicTeacherDTO teacher;

    /**
     * Тип недели.
     */
    @Schema(title = "Тип недели")
    @ReflectionField(value = "weekType.name", clazz = LessonModel.class)
    private String weekType;

    /**
     * День занятия.
     */
    @Schema(title = "День занятия")
    @ReflectionField(value = "day", clazz = LessonModel.class)
    private Short lessonDay;

    /**
     * Подгруппа.
     */
    @Schema(title = "Подгруппа")
    @ReflectionField(value = "subGroup.name", clazz = LessonModel.class)
    private String subGroup;

    /**
     * Номер занятия.
     */
    @Schema(title = "Номер занятия")
    @ReflectionField(value = "lessonNumber", clazz = LessonModel.class)
    private Short lessonNumber;

    /**
     * Корпус.
     */
    @Schema(title = "Корпус")
    @ReflectionField(value = "room.frame.id", clazz = LessonModel.class)
    private int frame;

    /**
     * Аудитория.
     */
    @Schema(title = "Аудитория")
    @ReflectionField(value = "room.roomNumber", clazz = LessonModel.class)
    private String location;

    /**
     * Название дисциплины.
     */
    @Schema(title = "Название дисциплины")
    @ReflectionField(value = "discipline.name", clazz = LessonModel.class)
    private String disciplineName;

    /**
     * Тип занятия.
     */
    @Schema(title = "Тип занятия")
    @ReflectionField(value = "lessonType.name", clazz = LessonModel.class)
    private String typeClassName;

    /**
     * Начала семестра.
     */
    @Schema(title = "Начала семестра")
    @ReflectionField(value = "startDate", clazz = LessonModel.class)
    private LocalDate startDate;

    /**
     * Конец семестра.
     */
    @Schema(title = "Конец семестра")
    @ReflectionField(value = "endDate", clazz = LessonModel.class)
    private LocalDate endDate;
}
