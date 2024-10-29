package by.vstu.dean.models.students;

import by.vstu.dean.core.adapters.LocalDateTimeTypeAdapter;
import by.vstu.dean.core.models.DBBaseModel;
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
@Table(name = "deviations")
@Schema(title = "Модель отклонения")
@Deprecated
public class DeviationModel extends DBBaseModel {

    @JoinColumn(name = "student_id")
    @ManyToOne
    @Schema(title = "Студент")
    private StudentModel student;
    @Schema(title = "Тип сообщения")
    private String msgType;
    @Schema(title = "Отчислен")
    private String expelled;
    @Schema(title = "Номер причины")
    private Integer reasonNumber;
    @Schema(title = "Сообщение")
    private String deviationMsg;
    @Schema(title = "Дата сообщения")
    @JsonAdapter(LocalDateTimeTypeAdapter.class)
    private LocalDateTime msgDate;
    @Schema(title = "Дата начала")
    @JsonAdapter(LocalDateTimeTypeAdapter.class)
    private LocalDateTime dateStart;
    @Schema(title = "Дата конца")
    @JsonAdapter(LocalDateTimeTypeAdapter.class)
    private LocalDateTime dateEnd;
    @Schema(title = "Новая фамилия")
    private String lastNameNew;
    @Schema(title = "Новая группа")
    private String groupNameNew;
    @Schema(title = "Сообщение 1")
    private String msg1;
    @Schema(title = "Сообщение 2")
    private String msg2;
    @Schema(title = "Приказ")
    private String commandMsg1;
    @Schema(title = "Приказ")
    private String commandMsg;
    @Schema(title = "Дата ликвидации")
    @JsonAdapter(LocalDateTimeTypeAdapter.class)
    private LocalDateTime dateLiquidation;
    @Schema(title = "Взыскание")
    private Integer penalty;

}
