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
@Schema(title = "DTO Студента")
public class V1PublicStudentDTO extends PublicDTO {

    /**
     * Фамилия студента.
     */
    @NotNull
    @Schema(title = "Фамилия")
    @ReflectionField(value = "lastName", clazz = StudentModel.class)
    private String surname;

    /**
     * Первая буква имени студента.
     */
    @NotNull
    @Schema(title = "Первая буква имени")
    private String fistLetterName;

    /**
     * Первая буква отчества студента.
     */
    @NotNull
    @Schema(title = "Первая буква отчества")
    private String fistLetterPatronymic;

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
     * URL-адрес фотографии студента
     */
    @Schema(title = "URL-адрес фотографии")
    @ReflectionField(value = "photoUrl", clazz = StudentModel.class)
    private String photoUrl;


}