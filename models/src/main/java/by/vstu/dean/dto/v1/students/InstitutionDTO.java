package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.students.InstitutionModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link InstitutionModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class InstitutionDTO extends BaseDTO {

    /**
     * Полное название учреждения образования.
     */
    @Schema(title = "Полное название")
    @ReflectionField(clazz = InstitutionModel.class)
    private String fullName;

    /**
     * Краткое название учреждения образования.
     */
    @Schema(title = "Краткое название")
    @ReflectionField(clazz = InstitutionModel.class)
    private String shortName;

}
