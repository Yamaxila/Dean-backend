package by.vstu.dean.models.internal;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.enums.EMessageType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

/**
 * Модель внутреннего сообщения.
 * <p>
 *     Внутренние сообщения - это сообщения, которые отправляются студентам
 *     в личный кабинет.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "internal_messages")
@Schema(title = "Модель внутреннего сообщения")
public class MessageModel extends DBBaseModel {

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
    private String title;
    /**
     * Текст сообщения.
     * <i>
     * Максимум 4096 символов.
     */
    @Size(max = 4096)
    @NotNull
    @Schema(title = "Текст сообщения",
            description = "Максимум 4096 символов",
            example = "Важное сообщение, которое нужно прочитать")
    private String message;
    /**
     * Флаг, указывающий, является ли сообщение неактивным.
     * <i>
     *     true - сообщение неактивно, false - активно.
     */
    @Schema(title = "Флаг, указывающий, является ли сообщение неактивным",
            description = "true - сообщение неактивно, false - активно",
            example = "false")
    private boolean inactive;
    /**
     * CRON-выражение для планирования отправки сообщения.
     * <i>
     *     Формат - <a href="https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/support/CronSequenceGenerator.html">...</a>
     */
    @Schema(title = "CRON-выражение для планирования отправки сообщения",
            description = "Формат - https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/scheduling/support/CronSequenceGenerator.html",
            example = "0 0 12 * * ?")
    private String cronExpression;
    /**
     * Тип сообщения.
     * <i>
     *     {@link EMessageType#INFO} - информационное сообщение,
     *     {@link EMessageType#WARNING} - предупреждение,
     *     {@link EMessageType#ERROR} - ошибка,
     *     {@link EMessageType#SCHEDULED} - запланированное сообщение.
     */
    @Enumerated(EnumType.ORDINAL)
    @NotNull
    @Schema(title = "Тип сообщения",
            description = "Тип сообщения: предупреждение, ошибка или запланированное сообщение",
            example = "INFO")
    private EMessageType messageType;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        MessageModel that = (MessageModel) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}