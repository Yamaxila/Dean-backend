package by.vstu.dean.future.models.students;

import by.vstu.dean.adapters.LocalDateTimeTypeAdapter;
import by.vstu.dean.future.DBBaseModel;
import com.google.gson.annotations.JsonAdapter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "deviations")
@ApiModel(description = "Объект отклонений")
@Deprecated
public class DeviationModel extends DBBaseModel {

    @JoinColumn(name = "student_id")
    @ManyToOne
    @ApiModelProperty(notes = "Студент")
    private StudentModel student;
    @ApiModelProperty(notes = "Тип сообщения")
    private String msgType;
    @ApiModelProperty(notes = "Отчислен")
    private String expelled;
    @ApiModelProperty(notes = "Номер причины")
    private Integer reasonNumber;
    @ApiModelProperty(notes = "Сообщение")
    private String deviationMsg;
    @ApiModelProperty(notes = "Дата сообщения")
    @JsonAdapter(LocalDateTimeTypeAdapter.class)
    private LocalDateTime msgDate;
    @ApiModelProperty(notes = "Дата начала")
    @JsonAdapter(LocalDateTimeTypeAdapter.class)
    private LocalDateTime dateStart;
    @ApiModelProperty(notes = "Дата конца")
    @JsonAdapter(LocalDateTimeTypeAdapter.class)
    private LocalDateTime dateEnd;
    @ApiModelProperty(notes = "Новая фамилия")
    private String lastNameNew;
    @ApiModelProperty(notes = "Новая группа")
    private String groupNameNew;
    @ApiModelProperty(notes = "Сообщение 1")
    private String msg1;
    @ApiModelProperty(notes = "Сообщение 2")
    private String msg2;
    @ApiModelProperty(notes = "Приказ")
    private String commandMsg1;
    @ApiModelProperty(notes = "Приказ")
    private String commandMsg;
    @ApiModelProperty(notes = "Дата ликвидации")
    @JsonAdapter(LocalDateTimeTypeAdapter.class)
    private LocalDateTime dateLiquidation;
    @ApiModelProperty(notes = "Взыскание")
    private Integer penalty;

}
