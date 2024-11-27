package by.vstu.dean.dto.v1.students;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.models.students.internal.ParentModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * DTO for {@link by.vstu.dean.models.students.internal.ParentModel}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(title = "DTO Студента")
public class V1ParentDTO extends BaseDTO {

    @ReflectionField(clazz = ParentModel.class)
    private String surname;
    @ReflectionField(clazz = ParentModel.class)
    private String name;
    @ReflectionField(clazz = ParentModel.class)
    private String patronymic;
    @ReflectionField(clazz = ParentModel.class)
    private String job;

    @ReflectionField(value = "phone.phone", clazz = ParentModel.class)
    private String phone;
    @ReflectionField(value = "phone.type.name", clazz = ParentModel.class)
    private String phoneType;

}
