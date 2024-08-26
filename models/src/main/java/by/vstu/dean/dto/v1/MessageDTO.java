package by.vstu.dean.dto.v1;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.enums.EMessageType;
import by.vstu.dean.models.internal.MessageModel;
import lombok.*;

import javax.validation.constraints.Size;

/**
 * DTO for {@link by.vstu.dean.models.internal.MessageModel}
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MessageDTO extends BaseDTO {
    @Size(max = 255)
    @ReflectionField(clazz = MessageModel.class)
    private String title;
    @Size(max = 4096)
    @ReflectionField(clazz = MessageModel.class)
    private String message;
    @ReflectionField(clazz = MessageModel.class)
    private boolean inactive;
    @ReflectionField(clazz = MessageModel.class)
    private String cronExpression;
    @ReflectionField(clazz = MessageModel.class)
    private EMessageType messageType;
}