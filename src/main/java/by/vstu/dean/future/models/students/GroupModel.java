package by.vstu.dean.future.models.students;

import by.vstu.dean.adapters.LocalDateTypeAdapter;
import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.FacultyModel;
import by.vstu.dean.future.models.specs.SpecialityModel;
import com.google.gson.annotations.JsonAdapter;
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

    @JsonAdapter(LocalDateTypeAdapter.class)
    private LocalDate dateStart, dateEnd;

    private Double score;

}
