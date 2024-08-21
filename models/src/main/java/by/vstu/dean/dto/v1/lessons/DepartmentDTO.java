package by.vstu.dean.dto.v1.lessons;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.dto.v1.FacultyDTO;
import by.vstu.dean.models.lessons.DepartmentModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/**
 * DTO for {@link by.vstu.dean.models.lessons.DepartmentModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public final class DepartmentDTO extends BaseDTO {

    /**
     * Название кафедры.
     */
    @NotNull
    @ApiModelProperty(notes = "Название кафедры")
    @ReflectionField(clazz = DepartmentModel.class)
    private String name;

    /**
     * Краткое название кафедры.
     */
    @NotNull
    @ApiModelProperty(notes = "Краткое название кафедры")
    @ReflectionField(clazz = DepartmentModel.class)
    private String shortName;

    /**
     * Факультет кафедры.
     */
    @ApiModelProperty(notes = "Факультет кафедры")
    private FacultyDTO faculty;

//    /**
//     * Все преподаватели, работающие на данной кафедре.
//     */
//    @ApiModelProperty(notes = "Все преподаватели, работающие на данной кафедре")
//    private Set<TeacherDTO> teachers;

}
