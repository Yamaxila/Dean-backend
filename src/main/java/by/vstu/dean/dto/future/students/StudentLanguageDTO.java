package by.vstu.dean.dto.future.students;

import by.vstu.dean.dto.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link by.vstu.dean.future.models.students.StudentLanguageModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class StudentLanguageDTO extends BaseDTO {

    /**
     * Название иностранного языка.
     */
    private String name;

}
