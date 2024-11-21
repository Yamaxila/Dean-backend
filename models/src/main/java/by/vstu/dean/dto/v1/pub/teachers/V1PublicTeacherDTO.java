package by.vstu.dean.dto.v1.pub.teachers;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.PublicDTO;
import by.vstu.dean.models.lessons.TeacherModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(title = "Публичное DTO Преподавателей")
public class V1PublicTeacherDTO extends PublicDTO {
    /**
     * Фамилия преподавателя.
     */
    @NotNull
    @Schema(title = "Фамилия")
    @ReflectionField(clazz = TeacherModel.class)
    private String surname;

    /**
     * Первая буква имени преподавателя.
     */
    @NotNull
    @Schema(title = "Первая буква имени")
    private String firstLetterName;

    /**
     * Первая буква отчества преподавателя.
     */
    @NotNull
    @Schema(title = "Первая буква отчества")
    private String firstLetterPatronymic;

//    /**
//     * Название кафедры.
//     */
//    @NotNull
//    @Schema(title = "Название кафедры")
//    private String departmentName;

    /**
     * Должность преподавателя.
     */
    @NotNull
    @Schema(title = "Должность преподавателя")
    private String degree;

    /**
     * Полное ФИО.
     */
    @SuppressWarnings("unused")
    public String getFullName() {
        return "%s %s. %s.".formatted(this.surname, this.firstLetterName, this.firstLetterPatronymic);
    }
}
