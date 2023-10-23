package by.vstu.dean.dto.future.lessons;

import by.vstu.dean.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * DTO for {@link by.vstu.dean.future.models.lessons.TeacherModel}
 */
@Data
public final class TeacherDTO extends BaseDTO {
    /**
     * Фамилия преподавателя.
     */
    @NotNull
    @ApiModelProperty(notes = "Фамилия преподавателя")
    private String surname;

    /**
     * Имя преподавателя.
     */
    @NotNull
    @ApiModelProperty(notes = "Имя преподавателя")
    private String name;

    /**
     * Отчество преподавателя.
     */
    @NotNull
    @ApiModelProperty(notes = "Отчество преподавателя")
    private String patronymic;

    /**
     * Должность преподавателя.
     */
    @ApiModelProperty(notes = "Должность преподавателя")
    private TeacherDegreeDTO degree;

    public String getFullName() {
        return String.format("%s %s %s", this.surname, this.name, this.patronymic);
    }
}
