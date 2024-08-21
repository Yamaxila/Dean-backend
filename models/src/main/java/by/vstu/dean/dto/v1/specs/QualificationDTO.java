package by.vstu.dean.dto.v1.specs;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.specs.QualificationModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * DTO for {@link by.vstu.dean.models.specs.QualificationModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class QualificationDTO extends BaseDTO {

    /**
     * Название квалификации.
     */
    @NotNull
    @ApiModelProperty(notes = "Название квалификации")
    @ReflectionField(clazz = QualificationModel.class)
    private String name;

    /**
     * Название квалификации в родительном падеже.
     */
    @NotNull
    @ApiModelProperty(notes = "Название квалификации в родительном падеже")
    @ReflectionField(clazz = QualificationModel.class)
    private String nameGenitive;

}
