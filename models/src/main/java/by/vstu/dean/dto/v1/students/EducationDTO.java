package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.students.EducationModel;
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
    @ReflectionField(clazz = EducationModel.class)
    private String education;

    /**
     * Тип документа об образовании.
     */
    @ApiModelProperty(notes = "Тип документа об образовании")
    @ReflectionField(clazz = EducationModel.class)
    private String educationDocumentType;

    /**
     * Серия документа об образовании.
     */
    @ApiModelProperty(notes = "Серия документа об образовании")
    @ReflectionField(clazz = EducationModel.class)
    private String educationDocumentSerial;

    /**
     * Номер документа об образовании.
     */
    @ApiModelProperty(notes = "Номер документа об образовании")
    @ReflectionField(clazz = EducationModel.class)
    private String educationDocumentNumber;

}
