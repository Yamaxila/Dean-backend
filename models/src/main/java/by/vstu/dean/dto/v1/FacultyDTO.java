package by.vstu.dean.dto.v1;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.FacultyModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link FacultyModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class FacultyDTO extends BaseDTO {

    /**
     * Краткое название факультета.
     */
    @Schema(title = "Краткое название")
    @ReflectionField(value = "shortName", clazz = FacultyModel.class)
    private String shortName;

    /**
     * Полное название факультета.
     */
    @Schema(title = "Полное название")
    @ReflectionField(value = "name", clazz = FacultyModel.class)
    private String name;


    @Override
    public String toString() {
        return "FacultyDTO[" +
                "id=" + this.getId() + ", " +
                "status=" + this.getStatus() + ", " +
                "shortName=" + shortName + ", " +
                "name=" + name + ']';
    }


}
