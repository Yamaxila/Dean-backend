package by.vstu.dean.dto.v1.lessons;

import by.vstu.dean.core.dto.BaseDTO;
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
    private String name;

    /**
     * Оплата за 1 час
     */
    @ApiModelProperty(notes = "Оплата за 1 час")
    private Double hourPrice;

}
