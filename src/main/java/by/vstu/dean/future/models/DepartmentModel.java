package by.vstu.dean.future.models;

import by.vstu.dean.future.DBBaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "departments")
public class DepartmentModel extends DBBaseModel {

    private String name;

}
