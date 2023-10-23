package by.vstu.dean.dto.future.lessons;

import by.vstu.dean.dto.BaseDTO;
import by.vstu.dean.dto.future.FacultyDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * DTO for {@link by.vstu.dean.future.models.lessons.DepartmentModel}
 */
@Data
public final class DepartmentDTO extends BaseDTO {

    /**
     * Название кафедры.
     */
    @NotNull
    @ApiModelProperty(notes = "Название кафедры")
    private String name;

    /**
     * Краткое название кафедры.
     */
    @NotNull
    @ApiModelProperty(notes = "Краткое название кафедры")
    private String shortName;

    /**
     * Факультет кафедры.
     */
    @ApiModelProperty(notes = "Факультет кафедры")
    private FacultyDTO faculty;

    /**
     * Все преподаватели, работающие на данной кафедре.
     */
    @ApiModelProperty(notes = "Все преподаватели, работающие на данной кафедре")
    private Set<TeacherDTO> teachers;

}
