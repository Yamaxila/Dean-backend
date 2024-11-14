package by.vstu.migrate.v1.models.specs;

import by.vstu.migrate.v1.V1DBBaseModel;
import by.vstu.migrate.v1.models.lessons.V1DepartmentModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

/**
 * Модель объекта специальности.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "specialities")
public class V1SpecialityModel extends V1DBBaseModel {

    /**
     * Название специальности.
     */
    @NotNull
    private String name;

    /**
     * Краткое название специальности.
     */
    private String shortName;

    /**
     * Код специальности.
     */
    private String specCode;

    /**
     * Кафедра, привязанная к специальности.
     */
    @NotFound(action = NotFoundAction.IGNORE)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private V1DepartmentModel department;

}
