package by.vstu.dean.dto.v1.lessons;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.enums.ExamType;
import by.vstu.dean.models.lessons.ExamModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;

/**
 * DTO for {@link by.vstu.dean.models.lessons.ExamModel}
 */
public final class ExamTypeDTO extends BaseDTO {

    /**
     * Название зачетной единицы.
     */
    @Schema(title = "Название зачетной единицы")
    @NotNull
    @SuppressWarnings("unused")
    @ReflectionField(clazz = ExamModel.class)
    private String name;

    /**
     * Тип экзамена.
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    @SuppressWarnings("unused")
    @ReflectionField(clazz = ExamModel.class)
    private ExamType type;

}
