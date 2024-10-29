package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.students.internal.EducationModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO {@link EducationModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(title = "DTO Образования студента")
public class V1EducationDTO extends BaseDTO {

    /**
     * Учреждение образования.
     */
    @Schema(title = "Учреждение образования")
    @ReflectionField(clazz = EducationModel.class)
    private String education;

    /**
     * Тип документа об образовании.
     */
    @Schema(title = "Тип документа об образовании")
    @ReflectionField(clazz = EducationModel.class)
    private String educationDocumentType;

    /**
     * Серия документа об образовании.
     */
    @Schema(title = "Серия документа об образовании")
    @ReflectionField(clazz = EducationModel.class)
    private String educationDocumentSerial;

    /**
     * Номер документа об образовании.
     */
    @Schema(title = "Номер документа об образовании")
    @ReflectionField(clazz = EducationModel.class)
    private String educationDocumentNumber;

}
