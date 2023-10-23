package by.vstu.dean.dto.future.specs;

import by.vstu.dean.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
* DTO for {@link by.vstu.dean.future.models.specs.QualificationModel}
*/
@Data
public class QualificationDTO extends BaseDTO {

    /**
     * Название квалификации.
     */
    @NotNull
    @ApiModelProperty(notes = "Название квалификации")
    private String name;

    /**
     * Название квалификации в родительном падеже.
     */
    @NotNull
    @ApiModelProperty(notes = "Название квалификации в родительном падеже")
    private String nameGenitive;

}
