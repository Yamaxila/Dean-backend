package by.vstu.dean.dto.v1.lessons;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.lessons.DisciplineModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link by.vstu.dean.models.lessons.DisciplineModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public final class DisciplineDTO extends BaseDTO {

    /**
     * Название дисциплины.
     */
    @Schema(title = "Название дисциплины")
    @NotNull
    @ReflectionField(clazz = DisciplineModel.class)
    private String name;

    /**
     * Краткое название дисциплины.
     */
    @Schema(title = "Краткое название дисциплины")
    @NotNull
    @ReflectionField(clazz = DisciplineModel.class)
    private String shortName;

    /**
     * Кафедра, к которой относится дисциплина.
     */
    @Schema(title = "Кафедра")
    private DepartmentDTO department;

}
