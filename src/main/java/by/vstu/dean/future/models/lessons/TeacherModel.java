package by.vstu.dean.future.models.lessons;

import by.vstu.dean.future.DBBaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(description = "Объект должности преподавателя")
public class TeacherModel extends DBBaseModel {

    @NotNull
    @ApiModelProperty(notes = "Фамилия преподавателя")
    private String lastName;
    @NotNull
    @ApiModelProperty(notes = "Имя преподавателя")
    private String firstName;
    @NotNull
    @ApiModelProperty(notes = "Отчество преподавателя")
    private String secondName;

    @JoinColumn(name = "degree_id")
    @ManyToOne
    @ApiModelProperty(notes = "Должность")
    private TeacherDegreeModel degree;

}
