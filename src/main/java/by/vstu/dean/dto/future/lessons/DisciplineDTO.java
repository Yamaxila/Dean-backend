package by.vstu.dean.dto.future.lessons;

import by.vstu.dean.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * DTO for {@link by.vstu.dean.future.models.lessons.DisciplineModel}
 */
@Data
public final class DisciplineDTO extends BaseDTO {

    /**
     * Название дисциплины.
     */
    @ApiModelProperty(notes = "Название дисциплины")
    @NotNull
    private String name;

    /**
     * Краткое название дисциплины.
     */
    @ApiModelProperty(notes = "Краткое название дисциплины")
    @NotNull
    private String shortName;

    /**
     * Кафедра, к которой относится дисциплина.
     */
    @ApiModelProperty(notes = "Кафедра")
    private DepartmentDTO department;

}
