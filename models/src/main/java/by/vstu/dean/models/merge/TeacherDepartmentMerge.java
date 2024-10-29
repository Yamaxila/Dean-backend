package by.vstu.dean.models.merge;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.models.lessons.DepartmentModel;
import by.vstu.dean.models.lessons.TeacherModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

/**
 * Модель объекта связи преподавателя и кафедры.
 */
@Entity
@Table(name = "teacher_department_merge")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Schema(title = "Модель связи преподаватель-кафедра")
public class TeacherDepartmentMerge extends DBBaseModel {

    /**
     * Преподаватель.
     */
    @JoinColumn(name = "teacher_id")
    @ManyToOne
    @Schema(title = "Преподаватель")
    @NotNull
    private TeacherModel teacher;

    /**
     * Кафедра.
     */
    @JoinColumn(name = "department_id")
    @ManyToOne
    @Schema(title = "Кафедра")
    @NotNull
    private DepartmentModel department;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        TeacherDepartmentMerge that = (TeacherDepartmentMerge) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
