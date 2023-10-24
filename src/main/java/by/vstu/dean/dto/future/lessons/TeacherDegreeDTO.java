package by.vstu.dean.dto.future.lessons;

import by.vstu.dean.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link by.vstu.dean.future.models.lessons.TeacherDegreeModel}
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
