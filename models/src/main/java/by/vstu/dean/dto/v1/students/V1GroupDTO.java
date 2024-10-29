package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.dto.v1.specs.V1SpecialityDTO;
import by.vstu.dean.models.students.GroupModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link GroupModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(title = "DTO Группы")
public final class V1GroupDTO extends BaseDTO {
    /**
     * Название группы.
     */
    @Schema(title = "Группа")
    @ReflectionField(clazz = GroupModel.class)
    private String name;

    /**
     * Специальность, связанная с данной группой.
     */
    @Schema(title = "Специальность")
    private V1SpecialityDTO spec;

    /**
     * Факультет, связанный с данной группой.
     */
    @Schema(title = "Факультет")
    @ReflectionField(value = "faculty.id", clazz = GroupModel.class)
    private Long facultyId;

    /**
     * Название факультета, связанного с данной группой.
     */
    @Schema(title = "Факультет")
    @ReflectionField(value = "faculty.name", clazz = GroupModel.class)
    private String facultyName;

    /**
     * Год поступления группы.
     */
    @Schema(title = "Год поступления")
    @ReflectionField(clazz = GroupModel.class)
    private Integer yearStart;

    /**
     * Год окончания обучения группы.
     */
    @Schema(title = "Год окончания")
    @ReflectionField(clazz = GroupModel.class)
    private Integer yearEnd;

    /**
     * Текущий курс группы.
     */
    @Schema(title = "Текущий курс группы")
    private Integer currentCourse;

}
