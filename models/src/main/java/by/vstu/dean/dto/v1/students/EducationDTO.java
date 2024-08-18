package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO {@link by.vstu.dean.models.students.EducationModel}
 */
@EqualsAndHashCode(callSuper = true)
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
