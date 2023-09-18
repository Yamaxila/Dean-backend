package by.vstu.dean.future.models;

import by.vstu.dean.future.DBBaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "groups")
public class GroupModel extends DBBaseModel {

    private String name;

    @ManyToOne
    @JoinColumn(name = "spec_id")
    private SpecialityModel spec;

    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private FacultyModel faculty;

//    @ManyToOne
//    @JoinColumn(name = "department_id")
//    private DepartmentModel department;

    private Integer yearStart, yearEnd;

    private LocalDate dateStart, dateEnd;

    private Double score;

}
