package by.vstu.dean.dto.v1.specs;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.specs.SpecialityModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link SpecialityModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(title = "DTO Специальности")
public final class SpecialityDTO extends BaseDTO {


    /**
     * Название специальности.
     */
    @NotNull
    @Schema(title = "Название специальности")
    @ReflectionField(clazz = SpecialityModel.class)
    private String name;

    /**
     * Краткое название специальности.
     */
    @Schema(title = "Краткое название")
    @ReflectionField(clazz = SpecialityModel.class)
    private String shortName;

    /**
     * Код специальности.
     */
    @Schema(title = "Код специальности")
    @ReflectionField(clazz = SpecialityModel.class)
    private String specCode;

    /**
     * Кафедра, привязанная к специальности.
     */
    @Schema(title = "Кафедра, привязанная к специальности")
    @ReflectionField(value = "department.id", clazz = SpecialityModel.class)
    private Long departmentId;

}
