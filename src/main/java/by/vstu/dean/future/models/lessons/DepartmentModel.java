package by.vstu.dean.future.models.lessons;

import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.FacultyModel;
import by.vstu.dean.future.models.merge.TeacherDepartmentMerge;
import by.vstu.dean.old.models.DFacultyModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "departments")
public class DepartmentModel extends DBBaseModel {

    private String name;
    private String shortName;
    private String room;
    @JoinColumn(name = "faculty_id")
    @ManyToOne
    @NotFound(action = NotFoundAction.IGNORE)
    private FacultyModel faculty;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "teacher_department_merge", joinColumns = {@JoinColumn(name = "id")}, inverseJoinColumns = {@JoinColumn(name = "teacher_id")})
    private Set<TeacherDepartmentMerge> teachers;
}
