package by.vstu.dean.future.models.lessons;

import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.FacultyModel;
import by.vstu.dean.future.models.merge.TeacherDepartmentMerge;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "departments")
@ApiModel(description = "Объект кафедры")
public class DepartmentModel extends DBBaseModel {

    @NotNull
    @ApiModelProperty(notes = "Название кафедры")
    private String name;
    @NotNull
    @ApiModelProperty(notes = "Краткое название кафедры")
    private String shortName;
    @NotNull
    @ApiModelProperty(notes = "Аудитория, где находится кафедра")
    private String room;
    @JoinColumn(name = "faculty_id")
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    @ApiModelProperty(notes = "Факультет кафедры")
    @JsonIgnore
    private FacultyModel faculty;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "teacher_department_merge", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "teacher_id")})
    @ApiModelProperty(notes = "Все преподаватели, работающие на данной кафедре")
    @JsonIgnore
    private Set<TeacherDepartmentMerge> teachers;


}
