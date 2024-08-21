package by.vstu.dean.dto.v1.specs;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.specs.SpecializationModel;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(notes = "Название специализации")
    @ReflectionField(clazz = SpecializationModel.class)
    private String name;

    /**
     * Краткое название специализации.
     */
    @ApiModelProperty(notes = "Краткое название")
    @ReflectionField(clazz = SpecializationModel.class)
    private String shortName;

    /**
     * Код специализации.
     */
    @ApiModelProperty(notes = "Код специальности")
    @ReflectionField(clazz = SpecializationModel.class)
    private String spezCode;

    /**
     * Специальность.
     */
    @ApiModelProperty(notes = "Специальность")
    private SpecialityDTO spec;

    /**
     * Квалификация.
     */
    @ApiModelProperty(notes = "Квалификация")
    @ReflectionField(value = "qualification.id", clazz = SpecializationModel.class)
    private Long qualificationId;

}
