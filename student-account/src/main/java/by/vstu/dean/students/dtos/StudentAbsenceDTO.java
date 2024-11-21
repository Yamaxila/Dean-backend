package by.vstu.dean.students.dtos;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.PublicDTO;
import by.vstu.dean.enums.ELessonType;
import by.vstu.dean.models.lessons.AbsenceModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * DTO Отработок для студентов
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "DTO Отработок для студентов")
public class StudentAbsenceDTO extends PublicDTO {

    /**
     * Дата.
     */
    @Schema(title = "Дата")
    @ReflectionField(clazz = AbsenceModel.class)
    private LocalDate date;

    /**
     * Дисциплина.
     */
    @Schema(title = "Дисциплина")
    @ReflectionField(value = "discipline.name", clazz = AbsenceModel.class)
    private String discipline;

    /**
     * Тип занятия.
     */
    @Schema(title = "Тип занятия")
    @ReflectionField(clazz = AbsenceModel.class)
    private ELessonType lessonType;

    /**
     * Номер занятия.
     */
    @Schema(title = "Номер занятия")
    @ReflectionField(clazz = AbsenceModel.class)
    private Integer lessonNumber;

    /**
     * Преподаватель.
     */
    @Schema(title = "Преподаватель")
    private String teacher;

    /**
     * Дата прогула.
     */
    @Schema(title = "Дата прогула")
    @ReflectionField(clazz = AbsenceModel.class)
    private Double absenceTime;

    /**
     * Дата закрытия отработки.
     */
    @Schema(title = "Дата закрытия отработки")
    @ReflectionField(clazz = AbsenceModel.class)
    private LocalDate dateCompleted;

    /**
     * Причина прогула.
     */
    @Schema(title = "Причина прогула")
    @ReflectionField(clazz = AbsenceModel.class)
    private String reasonMsg;

    /**
     * Распечатана.
     */
    @Schema(title = "Распечатана")
    @ReflectionField(clazz = AbsenceModel.class)
    private boolean printed;
}
