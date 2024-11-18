package by.vstu.migrate.v1.models.lessons;

import by.vstu.migrate.v1.V1DBBaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * Модель объекта дисциплины.
 */
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "disciplines")
public class V1DisciplineModel extends V1DBBaseModel {

    /**
     * Название дисциплины.
     */
    @NotNull
    private String name;

    /**
     * Краткое название дисциплины.
     */
    @NotNull
    private String shortName;

    /**
     * Кафедра, к которой относится дисциплина.
     */
    @JoinColumn(name = "department_id")
    @ManyToOne
    private V1DepartmentModel department;
}
