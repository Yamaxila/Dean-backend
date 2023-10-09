package by.vstu.dean.future.models.lessons;

import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.students.GroupModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Модель объекта учебного плана.
 */
@Entity
@Table(name = "study_plan")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Объект учебных планов")
@Getter
@Setter
public class StudyPlanModel extends DBBaseModel {

    /**
     * Группа.
     */
    @NotNull
    @JoinColumn(name = "group_id", nullable = false)
    @ManyToOne
    @ApiModelProperty(notes = "Группа")
    private GroupModel group;

    /**
     * Начало учебного года.
     */
    @ApiModelProperty(notes = "Начало учебного года")
    private Integer yearStart;

    /**
     * Конец учебного года.
     */
    @ApiModelProperty(notes = "Конец учебного года")
    private Integer yearEnd;

    /**
     * Номер семестра.
     */
    @ApiModelProperty(notes = "Номер семестра")
    private Integer semesterNumber;

    /**
     * Семестр.
     */
    @ApiModelProperty(notes = "Семестр")
    private String semester;

    /**
     * Курс группы.
     */
    @ApiModelProperty(notes = "Курс группы")
    private Integer course;

    /**
     * Тип зачетной единицы.
     */
    @NotNull
    @JoinColumn(name = "exam_type_id", nullable = false)
    @ManyToOne
    @ApiModelProperty(notes = "Тип зачетной единицы")
    private ExamModel exam;

    /**
     * Дисциплина.
     */
    @NotNull
    @JoinColumn(name = "discipline_id", nullable = false)
    @ManyToOne
    @ApiModelProperty(notes = "Дисциплина")
    private DisciplineModel discipline;

    /**
     * Преподаватель.
     */
    @NotNull
    @JoinColumn(name = "teacher_id", nullable = false)
    @ManyToOne
    @ApiModelProperty(notes = "Преподаватель")
    private TeacherModel teacher;

    public String toString() {
        return "StudyPlanModel(group=" + this.getGroup() + ", yearStart=" + this.getYearStart() + ", yearEnd=" + this.getYearEnd() + ", semesterNumber=" + this.getSemesterNumber() + ", semester=" + this.getSemester() + ", course=" + this.getCourse() + ", exam=" + this.getExam() + ", discipline=" + this.getDiscipline() + ", teacher=" + this.getTeacher() + ")";
    }
}
