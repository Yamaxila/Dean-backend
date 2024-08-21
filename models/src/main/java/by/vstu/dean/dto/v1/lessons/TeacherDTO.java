package by.vstu.dean.dto.v1.lessons;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.lessons.TeacherModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * DTO for {@link by.vstu.dean.models.lessons.TeacherModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public final class TeacherDTO extends BaseDTO {
    /**
     * Фамилия преподавателя.
     */
    @NotNull
    @ApiModelProperty(notes = "Фамилия преподавателя")
    @ReflectionField(clazz = TeacherModel.class)
    private String surname;

    /**
     * Имя преподавателя.
     */
    @NotNull
    @ApiModelProperty(notes = "Имя преподавателя")
    @ReflectionField(clazz = TeacherModel.class)
    private String name;

    /**
     * Отчество преподавателя.
     */
    @NotNull
    @ApiModelProperty(notes = "Отчество преподавателя")
    @ReflectionField(clazz = TeacherModel.class)
    private String patronymic;

    /**
     * Должность преподавателя.
     */
    @ApiModelProperty(notes = "Должность преподавателя")
    private TeacherDegreeDTO degree;

    @SuppressWarnings("unused")
    public String getFullName() {
        return String.format("%s %s %s", this.surname, this.name, this.patronymic);
    }
}
