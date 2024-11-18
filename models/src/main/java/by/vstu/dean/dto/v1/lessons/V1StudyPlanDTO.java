package by.vstu.dean.dto.v1.lessons;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.dto.v1.students.V1GroupDTO;
import by.vstu.dean.models.lessons.StudyPlanModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * DTO for {@link StudyPlanModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(title = "DTO Учебного плана")
public class V1StudyPlanDTO extends BaseDTO {

    /**
     * Группа
     */
    @Schema(title = "Группа")
    private V1GroupDTO group;

    /**
     * Начало учебного года.
     */
    @Schema(title = "Начало учебного года")
    @ReflectionField(clazz = StudyPlanModel.class)
    private Integer yearStart;

    /**
     * Конец учебного года.
     */
    @Schema(title = "Конец учебного года")
    @ReflectionField(clazz = StudyPlanModel.class)
    private Integer yearEnd;

    /**
     * Номер семестра.
     */
    @Schema(title = "Номер семестра")
    @ReflectionField(clazz = StudyPlanModel.class)
    private Integer semesterNumber;

    /**
     * Семестр.
     */
    @Schema(title = "Семестр")
    @ReflectionField(clazz = StudyPlanModel.class)
    private String semester;

    /**
     * Курс группы.
     */
    @Schema(title = "Курс группы")
    @ReflectionField(clazz = StudyPlanModel.class)
    private Integer course;

    /**
     * Тип зачетной единицы.
     */
    @Schema(title = "Тип зачетной единицы")
    private V1ExamTypeDTO exam;

    /**
     * Дисциплина.
     */
    @Schema(title = "Дисциплина")
    private V1DisciplineDTO discipline;

    /**
     * Преподаватель.
     */
    @Schema(title = "Преподаватель")
    private V1TeacherDTO teacher;

    /**
     * Количество часов
     */
    @Schema(title = "Количество часов")
    @ReflectionField(clazz = StudyPlanModel.class)
    private Integer hours;

    /**
     * Количество зачётных единиц
     */
    @Schema(title = "Количество зачётных единиц")
    @ReflectionField(clazz = StudyPlanModel.class)
    private BigDecimal testPoints;
}
