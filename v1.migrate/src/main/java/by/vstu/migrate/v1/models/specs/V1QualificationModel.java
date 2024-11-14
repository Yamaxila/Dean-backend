package by.vstu.migrate.v1.models.specs;

import by.vstu.migrate.v1.V1DBBaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

/**
 * Модель объекта квалификации.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "qualifications")
public class V1QualificationModel extends V1DBBaseModel {

    /**
     * Название квалификации.
     */
    @NotNull
    private String name;

    /**
     * Название квалификации в родительном падеже.
     */
    @NotNull
    private String nameGenitive;

}
