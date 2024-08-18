package by.vstu.dean.dto.v1.lessons;

import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.enums.ExamType;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

/**
 * DTO for {@link by.vstu.dean.models.lessons.ExamModel}
 */
public final class ExamTypeDTO extends BaseDTO {

    /**
     * Название зачетной единицы.
     */
    @ApiModelProperty(notes = "Название зачетной единицы")
    @NotNull
    @SuppressWarnings("unused")
    private String name;

    /**
     * Тип экзамена.
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    @SuppressWarnings("unused")
    private ExamType type;

}
