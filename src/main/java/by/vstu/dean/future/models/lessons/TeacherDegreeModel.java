package by.vstu.dean.future.models.lessons;

import by.vstu.dean.future.DBBaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "teacher_degrees")
@Data
@ApiModel(description = "Объект должности преподавателя")
public class TeacherDegreeModel extends DBBaseModel {

    @ApiModelProperty(notes = "Название должности")
    private String name;

}
