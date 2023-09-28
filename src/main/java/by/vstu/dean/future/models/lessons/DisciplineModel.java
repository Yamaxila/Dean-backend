package by.vstu.dean.future.models.lessons;

import by.vstu.dean.future.DBBaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "disciplines")
public class DisciplineModel extends DBBaseModel {

    private String name;
    private String shortName;
    @JoinColumn(name = "department_id")
    @ManyToOne
    DepartmentModel department;

}
