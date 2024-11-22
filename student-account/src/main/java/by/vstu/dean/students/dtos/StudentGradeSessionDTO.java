package by.vstu.dean.students.dtos;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.PublicDTO;
import by.vstu.dean.models.merge.StatementStudentMerge;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO Оценки.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "DTO Оценки")
public class StudentGradeSessionDTO extends PublicDTO {

    /**
     * Оценка.
     */
    @Schema(title = "Оценка")
    private String grade;

    /**
     * Дисциплина.
     */
    @Schema(title = "Дисциплина")
    @ReflectionField(value = "statement.studyPlan.discipline.name", clazz = StatementStudentMerge.class)
    private String disciplineName;

    /**
     * Дата занятия/экзамена.
     */
    @Schema(title = "Дата занятия/экзамена")
    @ReflectionField(value = "passDate", clazz = StatementStudentMerge.class)
    private LocalDate dateOfExam;

    /**
     * ФИО Преподавателей.
     */
    @Schema(title = "ФИО Преподавателей")
    private List<String> teachers;

    /**
     * Часы занятия.
     */
    @Schema(title = "Часы занятия")
    @ReflectionField(value = "statement.studyPlan.hours", clazz = StatementStudentMerge.class)
    private Integer hours;

    /**
     * Зачетные единицы.
     */
    @Schema(title = "Зачетные единицы")
    @ReflectionField(value = "statement.studyPlan.testPoints", clazz = StatementStudentMerge.class)
    private BigDecimal testPoints;

    /**
     * Номер экзаменационного листа.
     */
    @Schema(title = "Номер экзаменационного листа")
    @ReflectionField(value = "sheetNumber", clazz = StatementStudentMerge.class)
    private Integer examSheetNumber;

    /**
     * Пересдача.
     */
    @Schema(title = "Пересдача")
    @ReflectionField(value = "retake", clazz = StatementStudentMerge.class)
    private Boolean isRetake;

    /**
     * Форма контроля.
     */
    @ReflectionField(value = "statement.studyPlan.exam.name", clazz = StatementStudentMerge.class)
    @Schema(title = "Форма контроля")
    private String examType;

    /**
     * Номер семестра.
     */
    @Schema(title = "Номер семестра")
    private Integer semesterNumber;
}
