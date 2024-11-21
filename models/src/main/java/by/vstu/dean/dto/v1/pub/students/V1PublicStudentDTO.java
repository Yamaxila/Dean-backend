package by.vstu.dean.dto.v1.pub.students;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.PublicDTO;
import by.vstu.dean.dto.v1.students.V1GroupDTO;
import by.vstu.dean.models.students.StudentModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link StudentModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(title = "Публичное DTO Студента")
public class V1PublicStudentDTO extends PublicDTO {

    /**
     * Фамилия студента.
     */
    @NotNull
    @Schema(title = "Фамилия")
    @ReflectionField(clazz = StudentModel.class)
    private String surname;

    /**
     * Первая буква имени студента.
     */
    @NotNull
    @Schema(title = "Первая буква имени")
    private String firstLetterName;

    /**
     * Первая буква отчества студента.
     */
    @NotNull
    @Schema(title = "Первая буква отчества")
    private String firstLetterPatronymic;

    /**
     * Пол студента.
     */
    @Schema(title = "Пол")
    @ReflectionField(value = "sex", clazz = StudentModel.class)
    private Integer sex;

    /**
     * Группа, к которой принадлежит студент.
     */
    @NotNull
    @NotBlank
    private V1GroupDTO group;

    /**
     * Полное ФИО.
     */
    @SuppressWarnings("unused")
    public String getFullName() {
        return "%s %s. %s.".formatted(this.surname, this.firstLetterName, this.firstLetterPatronymic);
    }
}
