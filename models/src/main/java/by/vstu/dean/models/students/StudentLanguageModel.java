package by.vstu.dean.models.students;

import by.vstu.dean.core.models.DBBaseModel;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Класс, представляющий объект иностранного языка.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student_language")
@ApiModel(description = "Объект иностранного языка")
public class StudentLanguageModel extends DBBaseModel {

    /**
     * Название иностранного языка.
     */
    private String name;

}
