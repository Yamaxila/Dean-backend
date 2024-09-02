package by.vstu.dean.dto.v1.specs;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.specs.SpecializationModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link by.vstu.dean.models.specs.SpecializationModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public final class SpecializationDTO extends BaseDTO {

    /**
     * Название специализации.
     */
    @Schema(title = "Название специализации")
    @ReflectionField(clazz = SpecializationModel.class)
    private String name;

    /**
     * Краткое название специализации.
     */
    @Schema(title = "Краткое название")
    @ReflectionField(clazz = SpecializationModel.class)
    private String shortName;

    /**
     * Код специализации.
     */
    @Schema(title = "Код специальности")
    @ReflectionField(clazz = SpecializationModel.class)
    private String spezCode;

    /**
     * Специальность.
     */
    @Schema(title = "Специальность")
    private SpecialityDTO spec;

    /**
     * Квалификация.
     */
    @Schema(title = "Квалификация")
    @ReflectionField(value = "qualification.id", clazz = SpecializationModel.class)
    private Long qualificationId;

}
