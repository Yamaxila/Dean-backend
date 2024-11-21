package by.vstu.dean.models.merge;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.enums.EGrade;
import by.vstu.dean.models.lessons.StatementModel;
import by.vstu.dean.models.lessons.TeacherModel;
import by.vstu.dean.models.students.StudentModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import java.time.LocalDate;

/**
 * Модель объекта связи преподавателя и кафедры.
 */
@Entity
@Table(name = "dean_statement_student_merge")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Schema(title = "Модель связи ведомость-студент")
public class StatementStudentMerge extends DBBaseModel {

    /**
     * К какой ведомости относится запись
     */
    @JoinColumn(name = "statement_id")
    @ManyToOne(fetch = FetchType.EAGER)
    private StatementModel statement;

    /**
     * Дата сдачи зачётной единицы
     */
    @Schema(title = "Дата сдачи зачётной единицы")
    private LocalDate passDate;

    /**
     * Номер экзаменационного листа
     */
    @Schema(title = "Номер экзаменационного листа")
    @NotFound(action = NotFoundAction.IGNORE) // Пока я видел, что бывают пустые поля
    private Integer sheetNumber;

    /**
     * Студент, сдававший зачётную единицу
     */
    @Schema(title = "Студент, сдававший зачётную единицу")
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private StudentModel student;

    /**
     * Оценка, которую получил студент
     */
    @Schema(title = "Оценка, которую получил студент")
    @Enumerated(EnumType.STRING)
    private EGrade grade;

    /**
     * Преподаватель, который осуществлял оценку
     */
    @Schema(title = "Преподаватель, который осуществлял оценку")
    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private TeacherModel teacher;

    /**
     * Пересдача
     */
    @Column(columnDefinition = "boolean default false")
    @Schema(title = "Пересдача")
    private Boolean retake;

    /**
     * Номер попытки сдачи
     */
    @Schema(title = "Номер попытки сдачи.")
    private Integer attemptNumber;
}
