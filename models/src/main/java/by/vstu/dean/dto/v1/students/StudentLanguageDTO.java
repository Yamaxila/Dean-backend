package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.students.StudentLanguageModel;
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
    @ReflectionField(clazz = StudentLanguageModel.class)
    private String name;

}
