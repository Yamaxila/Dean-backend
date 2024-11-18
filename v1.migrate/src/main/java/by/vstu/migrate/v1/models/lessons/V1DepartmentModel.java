package by.vstu.migrate.v1.models.lessons;

import by.vstu.migrate.v1.V1DBBaseModel;
import by.vstu.migrate.v1.models.V1FacultyModel;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
public class V1DepartmentModel extends V1DBBaseModel {

    /**
     * Название кафедры.
     */
    @NotNull
    private String name;

    /**
     * Краткое название кафедры.
     */
    @NotNull
    private String shortName;

    /**
     * Аудитория, где находится кафедра.
     */
    @NotNull
    private String room;

    /**
     * Факультет кафедры.
     */
    @JoinColumn(name = "faculty_id")
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private V1FacultyModel faculty;

    /**
     * Все преподаватели, работающие на данной кафедре.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "teacher_department_merge", joinColumns = {@JoinColumn(name = "department_id")}, inverseJoinColumns = {@JoinColumn(name = "teacher_id")})
    @JsonManagedReference
    private Set<V1TeacherModel> teachers;
}
