package by.vstu.migrate.v1.models.lessons;

import by.vstu.migrate.v1.V1DBBaseModel;
import by.vstu.migrate.v1.models.students.V1GroupModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Модель объекта учебного плана.
 */
@Entity
@Table(name = "study_plan")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class V1StudyPlanModel extends V1DBBaseModel {

    /**
     * Группа.
     */
    @NotNull
    @JoinColumn(name = "group_id", nullable = false)
    @ManyToOne
    private V1GroupModel group;

    /**
     * Начало учебного года.
     */
    private Integer yearStart;

    /**
     * Конец учебного года.
     */
    private Integer yearEnd;

    /**
     * Номер семестра.
     */
    private Integer semesterNumber;

    /**
     * Семестр.
     */
    private String semester;

    /**
     * Курс группы.
     */
    private Integer course;

    /**
     * Тип зачетной единицы.
     */
    @NotNull
    @JoinColumn(name = "exam_type_id", nullable = false)
    @ManyToOne
    private V1ExamModel exam;

    /**
     * Дисциплина.
     */
    @NotNull
    @JoinColumn(name = "discipline_id", nullable = false)
    @ManyToOne
    private V1DisciplineModel discipline;

    /**
     * Преподаватель.
     */
    @NotNull
    @JoinColumn(name = "teacher_id", nullable = false)
    @ManyToOne
    private V1TeacherModel teacher;

    /**
     * Количество часов
     */
    private Integer hours;

    /**
     * Количество зачётных единиц
     */
    private BigDecimal testPoints;

    public String toString() {
        return "StudyPlanModel(group=" + this.getGroup() + ", yearStart=" + this.getYearStart() + ", yearEnd=" + this.getYearEnd() + ", semesterNumber=" + this.getSemesterNumber() + ", semester=" + this.getSemester() + ", course=" + this.getCourse() + ", exam=" + this.getExam() + ", discipline=" + this.getDiscipline() + ", teacher=" + this.getTeacher() + ")";
    }
}
