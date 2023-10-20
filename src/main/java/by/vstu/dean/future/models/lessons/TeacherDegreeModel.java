package by.vstu.dean.future.models.lessons;

import by.vstu.dean.future.DBBaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Модель объекта должности преподавателя.
 */
@Getter
@Entity
@ToString
@Table(name = "teacher_degrees")
@ApiModel(description = "Объект должности преподавателя")
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDegreeModel extends DBBaseModel {

    /**
     * Название должности.
     */
    @ApiModelProperty(notes = "Название должности")
    private String name;

    public String toString() {
        return "TeacherDegreeModel(name=" + this.getName() + ")";
    }
}
