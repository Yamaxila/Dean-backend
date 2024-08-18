package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link by.vstu.dean.models.students.InstitutionModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InstitutionDTO extends BaseDTO {

    /**
     * Полное название учреждения образования.
     */
    @ApiModelProperty(notes = "Полное название")
    private String fullName;

    /**
     * Краткое название учреждения образования.
     */
    @ApiModelProperty(notes = "Краткое название")
    private String shortName;

}
