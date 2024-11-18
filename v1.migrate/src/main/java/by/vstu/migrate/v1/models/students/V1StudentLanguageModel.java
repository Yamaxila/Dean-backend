package by.vstu.migrate.v1.models.students;

import by.vstu.migrate.v1.V1DBBaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс, представляющий объект иностранного языка.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "student_language")
public class V1StudentLanguageModel extends V1DBBaseModel {

    /**
     * Название иностранного языка.
     */
    private String name;

}
