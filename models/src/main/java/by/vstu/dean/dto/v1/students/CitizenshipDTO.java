package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.students.CitizenshipModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link by.vstu.dean.models.students.CitizenshipModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CitizenshipDTO extends BaseDTO {
    @ReflectionField(clazz = CitizenshipModel.class)
    private String name;
}