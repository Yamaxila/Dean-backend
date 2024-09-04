package by.vstu.dean.dto.v1.lessons;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.lessons.TeacherDegreeModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link TeacherDegreeModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TeacherDegreeDTO extends BaseDTO {

    /**
     * Название должности.
     */
    @Schema(title = "Название должности")
    @ReflectionField(clazz = TeacherDegreeModel.class)
    private String name;

    /**
     * Оплата за 1 час
     */
    @Schema(title = "Оплата за 1 час")
    @ReflectionField(clazz = TeacherDegreeModel.class)
    private Double hourPrice;

}
