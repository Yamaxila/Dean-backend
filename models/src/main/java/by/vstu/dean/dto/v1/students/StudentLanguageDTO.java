package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link by.vstu.dean.models.students.StudentLanguageModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StudentLanguageDTO extends BaseDTO {

    /**
     * Название иностранного языка.
     */
    private String name;

}
