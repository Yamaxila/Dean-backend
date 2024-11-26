package by.vstu.dean.core.dto;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.models.DBBaseModel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class BaseDTO extends PublicDTO {

    @ReflectionField(value = "sourceId", clazz = DBBaseModel.class)
    private Long sourceId;
    @ReflectionField(value = "updated", clazz = DBBaseModel.class)
    private LocalDateTime updated;


}
