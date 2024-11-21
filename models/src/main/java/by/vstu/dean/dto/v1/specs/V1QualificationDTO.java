package by.vstu.dean.dto.v1.specs;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.specs.QualificationModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link QualificationModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(title = "DTO Квалификации")
public class V1QualificationDTO extends BaseDTO {

    /**
     * Название квалификации.
     */
    @NotNull
    @Schema(title = "Название квалификации")
    @ReflectionField(clazz = QualificationModel.class)
    private String name;

    /**
     * Название квалификации в родительном падеже.
     */
    @NotNull
    @Schema(title = "Название квалификации в родительном падеже")
    @ReflectionField(clazz = QualificationModel.class)
    private String nameGenitive;

}
