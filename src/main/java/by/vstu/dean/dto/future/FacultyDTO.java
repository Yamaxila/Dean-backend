package by.vstu.dean.dto.future;

import by.vstu.dean.dto.BaseDTO;
import by.vstu.dean.future.models.FacultyModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * DTO for {@link FacultyModel}
 */
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
