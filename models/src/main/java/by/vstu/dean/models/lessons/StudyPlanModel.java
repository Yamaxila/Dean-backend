package by.vstu.dean.models.lessons;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.models.students.GroupModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Objects;

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

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        StudyPlanModel that = (StudyPlanModel) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
