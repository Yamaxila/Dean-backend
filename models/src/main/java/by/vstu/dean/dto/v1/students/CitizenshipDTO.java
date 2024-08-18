package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link by.vstu.dean.models.students.CitizenshipModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CitizenshipDTO extends BaseDTO {
    private String name;
}