package by.vstu.dean.future.models.lessons;

import by.vstu.dean.enums.ExamType;
import by.vstu.dean.future.DBBaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="exams")
@Data
@ApiModel(description = "Объект зачетной единицы")
public class ExamModel extends DBBaseModel {
    @ApiModelProperty(notes = "Название зачетной единицы")
    @NotNull
    private String name;

    @Enumerated(EnumType.ORDINAL)
    @NotNull
    private ExamType type;

}
