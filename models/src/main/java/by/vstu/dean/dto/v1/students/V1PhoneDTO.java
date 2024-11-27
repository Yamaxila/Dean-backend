package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.enums.EMobileOperatorType;
import by.vstu.dean.models.students.internal.PhoneModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link by.vstu.dean.models.students.internal.PhoneModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(title = "DTO Студента")
public class V1PhoneDTO extends BaseDTO {

    @Enumerated(EnumType.ORDINAL)
    private EMobileOperatorType type;

    @ReflectionField(clazz = PhoneModel.class)
    private String phone;

}
