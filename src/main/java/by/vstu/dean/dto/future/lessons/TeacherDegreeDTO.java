package by.vstu.dean.dto.future.lessons;

import by.vstu.dean.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * DTO for {@link by.vstu.dean.future.models.lessons.TeacherDegreeModel}
 */
@Data
public class TeacherDegreeDTO extends BaseDTO {

    /**
     * Название должности.
     */
    @ApiModelProperty(notes = "Название должности")
    private String name;

}
