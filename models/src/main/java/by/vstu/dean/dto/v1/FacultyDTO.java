package by.vstu.dean.dto.v1;

import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.FacultyModel;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(notes = "Краткое название")
    private String shortName;

    /**
     * Полное название факультета.
     */
    @ApiModelProperty(notes = "Полное название")
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
