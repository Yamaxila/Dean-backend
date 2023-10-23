package by.vstu.dean.dto.future.students;

import by.vstu.dean.dto.BaseDTO;
import lombok.Data;

/**
 * DTO for {@link by.vstu.dean.future.models.students.StudentLanguageModel}
 */
@Data
public class StudentLanguageDTO extends BaseDTO {

    /**
     * Название иностранного языка.
     */
    private String name;

}
