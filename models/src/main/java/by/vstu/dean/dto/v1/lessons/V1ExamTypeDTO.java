package by.vstu.dean.dto.v1.lessons;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.enums.ExamType;
import by.vstu.dean.models.lessons.ExamModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link ExamModel}
 */
@Schema(title = "DTO Типа зачетной единицы")
@EqualsAndHashCode(callSuper = true)
@Data
public final class V1ExamTypeDTO extends BaseDTO {

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
