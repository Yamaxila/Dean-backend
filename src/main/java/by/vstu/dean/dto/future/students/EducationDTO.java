package by.vstu.dean.dto.future.students;

import by.vstu.dean.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * DTO {@link by.vstu.dean.future.models.students.EducationModel}
 */
@Data
public class EducationDTO extends BaseDTO {

    /**
     * Учреждение образования.
     */
    @ApiModelProperty(notes = "Учреждение образования")
    private String education;

    /**
     * Тип документа об образовании.
     */
    @ApiModelProperty(notes = "Тип документа об образовании")
    private String educationDocumentType;

    /**
     * Серия документа об образовании.
     */
    @ApiModelProperty(notes = "Серия документа об образовании")
    private String educationDocumentSerial;

    /**
     * Номер документа об образовании.
     */
    @ApiModelProperty(notes = "Номер документа об образовании")
    private String educationDocumentNumber;

}
