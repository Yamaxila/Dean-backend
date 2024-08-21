package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.anotations.ReflectionField;
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
    @ReflectionField(clazz = GroupModel.class)
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
    @ReflectionField(value = "faculty.id", clazz = GroupModel.class)
    private Long facultyId;

    /**
     * Название факультета, связанного с данной группой.
     */
    @ApiModelProperty(notes = "Факультет")
    @ReflectionField(value = "faculty.name", clazz = GroupModel.class)
    private String facultyName;

    /**
     * Год поступления группы.
     */
    @ApiModelProperty(notes = "Год поступления")
    @ReflectionField(clazz = GroupModel.class)
    private Integer yearStart;

    /**
     * Год окончания обучения группы.
     */
    @ApiModelProperty(notes = "Год окончания")
    @ReflectionField(clazz = GroupModel.class)
    private Integer yearEnd;

    /**
     * Текущий курс группы.
     */
    @ApiModelProperty(notes = "Текущий курс группы")
    private Integer currentCourse;

}
