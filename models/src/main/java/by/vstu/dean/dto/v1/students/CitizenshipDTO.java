package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.students.CitizenshipModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link CitizenshipModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(title = "DTO Гражданства")
public class CitizenshipDTO extends BaseDTO {
    @ReflectionField(clazz = CitizenshipModel.class)
    private String name;
}