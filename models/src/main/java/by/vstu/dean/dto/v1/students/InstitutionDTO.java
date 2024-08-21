package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.students.InstitutionModel;
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
    @ReflectionField(clazz = InstitutionModel.class)
    private String fullName;

    /**
     * Краткое название учреждения образования.
     */
    @ApiModelProperty(notes = "Краткое название")
    @ReflectionField(clazz = InstitutionModel.class)
    private String shortName;

}
