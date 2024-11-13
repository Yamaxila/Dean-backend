package by.vstu.dean.models.changes;

import by.vstu.dean.core.models.DBBaseModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dean_reasons")
@Schema(title = "Модель причины изменения студента")
public class ReasonModel extends DBBaseModel {

    private String name;


}
