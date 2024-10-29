package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.students.internal.StudentLanguageModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link StudentLanguageModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(title = "DTO Иностранного языка студента")
public class V1StudentLanguageDTO extends BaseDTO {

    /**
     * Название иностранного языка.
     */
    @ReflectionField(clazz = StudentLanguageModel.class)
    private String name;

}
