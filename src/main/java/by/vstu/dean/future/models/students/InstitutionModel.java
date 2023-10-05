package by.vstu.dean.future.models.students;

import by.vstu.dean.future.DBBaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "institutions")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class InstitutionModel extends DBBaseModel {

    private String fullName;
    private String shortName;

}
