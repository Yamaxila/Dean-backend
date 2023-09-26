package by.vstu.dean.future.models;

import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.old.models.DFacultyModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
}
