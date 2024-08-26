package by.vstu.dean.models.internal;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.enums.EMessageType;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * Класс, представляющий объект внутреннего сообщения.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "internal_messages")
@ApiModel(description = "Объект внутреннего сообщения")
public class MessageModel extends DBBaseModel {

    @Size(max = 255)
    private String title;
    @Size(max = 4096)
    private String message;
    private boolean inactive;
    private String cronExpression;
    @Enumerated(EnumType.ORDINAL)
    private EMessageType messageType;

}
