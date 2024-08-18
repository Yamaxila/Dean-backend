package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.dto.v1.specs.SpecialityDTO;
import by.vstu.dean.models.students.GroupModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link GroupModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public final class GroupDTO extends BaseDTO {
    /**
     * Название группы.
     */
    @ApiModelProperty(notes = "Группа")
    private String name;

    /**
     * Специальность, связанная с данной группой.
     */
    @ApiModelProperty(notes = "Специальность")
    private SpecialityDTO spec;

    /**
     * Факультет, связанный с данной группой.
     */
    @ApiModelProperty(notes = "Факультет")
    private Long facultyId;

    /**
     * Год поступления группы.
     */
    @ApiModelProperty(notes = "Год поступления")
    private Integer yearStart;

    /**
     * Год окончания обучения группы.
     */
    @ApiModelProperty(notes = "Год окончания")
    private Integer yearEnd;

    /**
     * Текущий курс группы.
     */
    private Integer currentCourse;

}
