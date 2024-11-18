package by.vstu.migrate.v1.models.students;

import by.vstu.migrate.v1.V1DBBaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Модель объекта гражданства.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "citizenhips")
public class V1CitizenshipModel extends V1DBBaseModel {

    /**
     * Страна (название).
     */
    private String name;

}
