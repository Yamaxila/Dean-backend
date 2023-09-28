package by.vstu.dean.future.models.lessons;

import by.vstu.dean.future.DBBaseModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TeacherModel extends DBBaseModel {


    private String lastName, firstName, secondName;

    private String degree;

}
