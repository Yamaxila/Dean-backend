package by.vstu.dean.models.lessons;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.models.students.GroupModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.proxy.HibernateProxy;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Модель объекта учебного плана.
 */
@Entity
@Table(name = "dean_study_plan")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Schema(title = "Модель учебного плана")
public class StudyPlanModel extends DBBaseModel {

    /**
     * Группа.
     */
    @NotNull
    @JoinColumn(name = "group_id", nullable = false)
    @ManyToOne(fetch = FetchType.EAGER)
    @Schema(title = "Группа")
    private GroupModel group;

    /**
     * Начало учебного года.
     */
    @Schema(title = "Начало учебного года")
    private Integer yearStart;

    /**
     * Конец учебного года.
     */
    @Schema(title = "Конец учебного года")
    private Integer yearEnd;

    /**
     * Номер семестра.
     */
    @Schema(title = "Номер семестра")
    private Integer semesterNumber;

    /**
     * Семестр.
     */
    @Schema(title = "Семестр")
    private String semester;

    /**
     * Курс группы.
     */
    @Schema(title = "Курс группы")
    private Integer course;

    /**
     * Тип зачетной единицы.
     */
    @JoinColumn(name = "exam_type_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @Schema(title = "Тип зачетной единицы")
    private ExamModel exam;

    /**
     * Дисциплина.
     */
    @JoinColumn(name = "discipline_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @Schema(title = "Дисциплина")
    private DisciplineModel discipline;

    /**
     * Преподаватель.
     */
    @JoinColumn(name = "teacher_id")
    @ManyToOne(fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    @Schema(title = "Преподаватель")
    private TeacherModel teacher;

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
