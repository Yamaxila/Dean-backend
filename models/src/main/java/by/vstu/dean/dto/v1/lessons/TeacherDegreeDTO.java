package by.vstu.dean.dto.v1.lessons;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.lessons.TeacherDegreeModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link by.vstu.dean.models.lessons.TeacherDegreeModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeacherDegreeDTO extends BaseDTO {

    /**
     * Название должности.
     */
    @ApiModelProperty(notes = "Название должности")
    @ReflectionField(clazz = TeacherDegreeModel.class)
    private String name;

    /**
     * Оплата за 1 час
     */
    @ApiModelProperty(notes = "Оплата за 1 час")
    @ReflectionField(clazz = TeacherDegreeModel.class)
    private Double hourPrice;

}
