package by.vstu.dean.models.lessons;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.models.FacultyModel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

import java.util.Objects;
import java.util.Set;

/**
 * Модель объекта кафедры.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "departments")
@Schema(title = "Модель кафедры")
public class DepartmentModel extends DBBaseModel {

    /**
     * Название кафедры.
     */
    @Schema(title = "Название кафедры")
    @NotNull
    private String name;

    /**
     * Краткое название кафедры.
     */
    @Schema(title = "Краткое название кафедры")
    @NotNull
    private String shortName;

    /**
     * Аудитория, где находится кафедра.
     */
    @Schema(title = "Аудитория, где находится кафедра")
    @NotNull
    private String room;

    /**
     * Факультет кафедры.
     */
    @JoinColumn(name = "faculty_id")
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE) //FIXME: почему?
    @Schema(title = "Факультет кафедры")
    private FacultyModel faculty;

    /**
     * Все преподаватели, работающие на данной кафедре.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "teacher_department_merge", joinColumns = {@JoinColumn(name = "department_id")}, inverseJoinColumns = {@JoinColumn(name = "teacher_id")})
    @Schema(title = "Все преподаватели, работающие на данной кафедре")
    @JsonManagedReference
    private Set<TeacherModel> teachers;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        DepartmentModel that = (DepartmentModel) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
