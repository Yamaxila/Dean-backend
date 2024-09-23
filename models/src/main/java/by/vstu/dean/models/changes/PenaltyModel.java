package by.vstu.dean.models.changes;

import by.vstu.dean.core.adapters.LocalDateTimeTypeAdapter;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.models.students.StudentModel;
import com.google.gson.annotations.JsonAdapter;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "penalties")
@Schema(title = "Модель взыскания")
public class PenaltyModel extends DBBaseModel {

    @JoinColumn(name = "student_id")
    @ManyToOne
    @Schema(title = "Студент")
    private StudentModel student;
    @Schema(title = "Дата ликвидации")
    @JsonAdapter(LocalDateTimeTypeAdapter.class)
    private LocalDateTime dateLiquidation;
    @JoinColumn(name = "reason_id")
    @ManyToOne
    @Schema(title = "Причина взыскания")
    private ReasonModel reason;
    private String orderNumber;
    private String orderText;
}
