package by.vstu.dean.future.models.students;

import by.vstu.dean.future.DBBaseModel;
import io.swagger.annotations.ApiModel;
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
@Table(name = "student_language")
@ApiModel(description = "Объект иностранного языка")
public class StudentLanguageModel extends DBBaseModel {

    private String name;

}
