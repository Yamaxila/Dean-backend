package by.vstu.dean.dto.v1;

import by.vstu.dean.core.anotations.ReflectionField;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.enums.EMessageType;
import by.vstu.dean.models.internal.MessageModel;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
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
public class V1MessageDTO extends BaseDTO {
    /**
     * Заголовок сообщения.
     * <i>
     * Максимум 255 символов.
     */
    @Size(max = 255)
    @NotNull
    @Schema(title = "Заголовок сообщения",
            description = "Максимум 255 символов",
            example = "Важное сообщение")
    @ReflectionField(clazz = MessageModel.class)
    private String title;
    /**
     * Текст сообщения.
     * <i>
     *     Максимум 4096 символов.
     */
    @Size(max = 4096)
    @NotNull
    @Schema(title = "Текст сообщения",
            description = "Максимум 4096 символов",
            example = "Важное сообщение, которое нужно прочитать")
    @ReflectionField(clazz = MessageModel.class)
    private String message;
    /**
     * Флаг, указывающий, является ли сообщение неактивным.
     * <i>
     *     true - сообщение неактивно, false - активно.
     */
    @Schema(title = "Флаг, указывающий, является ли сообщение неактивным",
            description = "true - сообщение неактивно, false - активно",
            example = "false")
    @ReflectionField(clazz = MessageModel.class)
    private boolean inactive;
    /**
     * CRON-выражение для планирования отправки сообщения.
     * <i>
     *     Формат - <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/support/CronSequenceGenerator.html">...</a>
     */
    @Schema(title = "CRON-выражение для планирования отправки сообщения",
            description = "Формат - https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/support/CronSequenceGenerator.html",
            example = "0 0 12 * * ?")
    @ReflectionField(clazz = MessageModel.class)
    private String cronExpression;
    /**
     * Тип сообщения.
     * <i>
     *     {@link EMessageType#INFO} - информационное сообщение,
     *     {@link EMessageType#WARNING} - предупреждение,
     *     {@link EMessageType#ERROR} - ошибка,
     *     {@link EMessageType#SCHEDULED} - запланированное сообщение.
     */
    @ReflectionField(clazz = MessageModel.class)
    @Schema(title = "Тип сообщения",
            description = "Тип сообщения: предупреждение, ошибка или запланированное сообщение",
            example = "INFO")
    private EMessageType messageType;
}