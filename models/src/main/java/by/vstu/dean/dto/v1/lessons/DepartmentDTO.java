package by.vstu.dean.dto.v1.lessons;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.dto.v1.FacultyDTO;
import by.vstu.dean.models.lessons.DepartmentModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link DepartmentModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public final class DepartmentDTO extends BaseDTO {

    /**
     * Название кафедры.
     */
    @NotNull
    @Schema(title = "Название кафедры")
    @ReflectionField(clazz = DepartmentModel.class)
    private String name;

    /**
     * Краткое название кафедры.
     */
    @NotNull
    @Schema(title = "Краткое название кафедры")
    @ReflectionField(clazz = DepartmentModel.class)
    private String shortName;

    /**
     * Факультет кафедры.
     */
    @Schema(title = "Факультет кафедры")
    private FacultyDTO faculty;

//    /**
//     * Все преподаватели, работающие на данной кафедре.
//     */
//    @Schema(title = "Все преподаватели, работающие на данной кафедре")
//    private Set<TeacherDTO> teachers;

}
