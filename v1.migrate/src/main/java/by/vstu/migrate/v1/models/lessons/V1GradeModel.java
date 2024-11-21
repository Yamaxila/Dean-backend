package by.vstu.migrate.v1.models.lessons;

import by.vstu.migrate.v1.V1DBBaseModel;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

/**
 * Модель объекта оценки.
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "grades")
@Getter
@Setter
@ToString
public class V1GradeModel extends V1DBBaseModel {

    /**
     * Система оценивания
     */
    private Integer range;

    /**
     * Оценка
     */
    private String grade;
}
