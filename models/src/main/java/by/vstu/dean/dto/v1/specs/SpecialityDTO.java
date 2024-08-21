package by.vstu.dean.dto.v1.specs;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.specs.SpecialityModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * DTO for {@link by.vstu.dean.models.specs.SpecialityModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public final class SpecialityDTO extends BaseDTO {


    /**
     * Название специальности.
     */
    @NotNull
    @ApiModelProperty(notes = "Название специальности")
    @ReflectionField(clazz = SpecialityModel.class)
    private String name;

    /**
     * Краткое название специальности.
     */
    @ApiModelProperty(notes = "Краткое название")
    @ReflectionField(clazz = SpecialityModel.class)
    private String shortName;

    /**
     * Код специальности.
     */
    @ApiModelProperty(notes = "Код специальности")
    @ReflectionField(clazz = SpecialityModel.class)
    private String specCode;

    /**
     * Кафедра, привязанная к специальности.
     */
    @ApiModelProperty(notes = "Кафедра, привязанная к специальности")
    @ReflectionField(value = "department.id", clazz = SpecialityModel.class)
    private Long departmentId;

}
