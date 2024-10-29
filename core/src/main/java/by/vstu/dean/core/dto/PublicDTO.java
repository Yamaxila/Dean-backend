package by.vstu.dean.core.dto;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.models.DBBaseModel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublicDTO {

    @ReflectionField(value = "id", clazz = DBBaseModel.class)
    private Long id;
    @ReflectionField(value = "status", clazz = DBBaseModel.class)
    private EStatus status;

}
