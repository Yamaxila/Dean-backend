package by.vstu.migrate.v1.models.lessons;

import by.vstu.migrate.v1.V1DBBaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Модель объекта должности преподавателя.
 */
@Getter
@Entity
@Table(name = "teacher_degrees")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class V1TeacherDegreeModel extends V1DBBaseModel {

    /**
     * Название должности.
     */
    private String name;

    /**
     * Оплата за 1 час
     */
    private Double hourPrice;

    public String toString() {
        return "TeacherDegreeModel(name=" + this.getName() + ")";
    }
}
