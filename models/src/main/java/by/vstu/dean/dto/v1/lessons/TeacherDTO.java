package by.vstu.dean.dto.v1.lessons;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.lessons.TeacherModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link TeacherModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(title = "DTO Преподавателя")
public final class TeacherDTO extends BaseDTO {
    /**
     * Фамилия преподавателя.
     */
    @NotNull
    @Schema(title = "Фамилия преподавателя")
    @ReflectionField(clazz = TeacherModel.class)
    private String surname;

    /**
     * Имя преподавателя.
     */
    @NotNull
    @Schema(title = "Имя преподавателя")
    @ReflectionField(clazz = TeacherModel.class)
    private String name;

    /**
     * Отчество преподавателя.
     */
    @NotNull
    @Schema(title = "Отчество преподавателя")
    @ReflectionField(clazz = TeacherModel.class)
    private String patronymic;

    /**
     * Должность преподавателя.
     */
    @Schema(title = "Должность преподавателя")
    private TeacherDegreeDTO degree;

    @SuppressWarnings("unused")
    public String getFullName() {
        return String.format("%s %s %s", this.surname, this.name, this.patronymic);
    }
}
