package by.vstu.migrate.v1.models.students;

import by.vstu.migrate.v1.V1DBBaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Класс, представляющий объект учреждения образования.
 */
@Entity
@Table(name = "institutions")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class V1InstitutionModel extends V1DBBaseModel {

    /**
     * Полное название учреждения образования.
     */
    private String fullName;

    /**
     * Краткое название учреждения образования.
     */
    private String shortName;

}
