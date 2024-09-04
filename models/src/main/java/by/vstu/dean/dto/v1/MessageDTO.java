package by.vstu.dean.dto.v1;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.enums.EMessageType;
import by.vstu.dean.models.internal.MessageModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * DTO for {@link MessageModel}
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Schema(title = "DTO Внутреннего сообщения")
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