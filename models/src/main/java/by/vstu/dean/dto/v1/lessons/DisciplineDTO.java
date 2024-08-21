package by.vstu.dean.dto.v1.lessons;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.lessons.DisciplineModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * DTO for {@link by.vstu.dean.models.lessons.DisciplineModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public final class DisciplineDTO extends BaseDTO {

    /**
     * Название дисциплины.
     */
    @ApiModelProperty(notes = "Название дисциплины")
    @NotNull
    @ReflectionField(clazz = DisciplineModel.class)
    private String name;

    /**
     * Краткое название дисциплины.
     */
    @ApiModelProperty(notes = "Краткое название дисциплины")
    @NotNull
    @ReflectionField(clazz = DisciplineModel.class)
    private String shortName;

    /**
     * Кафедра, к которой относится дисциплина.
     */
    @ApiModelProperty(notes = "Кафедра")
    private DepartmentDTO department;

}
