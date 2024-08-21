package by.vstu.dean.models.lessons;

import by.vstu.dean.core.models.DBBaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Модель объекта должности преподавателя.
 */
@Getter
@Entity
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

    /**
     * Оплата за 1 час
     */
    @ApiModelProperty(notes = "Оплата за 1 час")
    private Double hourPrice;

    public String toString() {
        return "TeacherDegreeModel(name=" + this.getName() + ")";
    }
}
