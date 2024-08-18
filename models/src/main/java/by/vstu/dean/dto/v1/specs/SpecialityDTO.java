package by.vstu.dean.dto.v1.specs;

import by.vstu.dean.core.dto.BaseDTO;
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
    private String name;

    /**
     * Краткое название специальности.
     */
    @ApiModelProperty(notes = "Краткое название")
    private String shortName;

    /**
     * Код специальности.
     */
    @ApiModelProperty(notes = "Код специальности")
    private String specCode;

    /**
     * Кафедра, привязанная к специальности.
     */
    @ApiModelProperty(notes = "Кафедра, привязанная к специальности")
    private Long departmentId;

}
