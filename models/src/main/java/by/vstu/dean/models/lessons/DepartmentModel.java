package by.vstu.dean.models.lessons;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.models.FacultyModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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
@ApiModel(description = "Объект кафедры")
public class DepartmentModel extends DBBaseModel {

    /**
     * Название кафедры.
     */
    @NotNull
    @ApiModelProperty(notes = "Название кафедры")
    private String name;

    /**
     * Краткое название кафедры.
     */
    @NotNull
    @ApiModelProperty(notes = "Краткое название кафедры")
    private String shortName;

    /**
     * Аудитория, где находится кафедра.
     */
    @NotNull
    @ApiModelProperty(notes = "Аудитория, где находится кафедра")
    private String room;

    /**
     * Факультет кафедры.
     */
    @JoinColumn(name = "faculty_id")
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @ApiModelProperty(notes = "Факультет кафедры")
    private FacultyModel faculty;

    /**
     * Все преподаватели, работающие на данной кафедре.
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "teacher_department_merge", joinColumns = {@JoinColumn(name = "department_id")}, inverseJoinColumns = {@JoinColumn(name = "teacher_id")})
    @ApiModelProperty(notes = "Все преподаватели, работающие на данной кафедре")
    private Set<TeacherModel> teachers;
}
