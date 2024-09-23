package by.vstu.dean.models.changes;

import by.vstu.dean.core.models.DBBaseModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "student_changes")
@Schema(title = "Модель изменений студента")
public class ChangeModel extends DBBaseModel {

    @JoinColumn(name = "student_change_id")
    @ManyToOne
    private StudentChangeModel change;

    private String changedField;
    private String oldValue;
    private String newValue;

}
