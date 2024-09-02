package by.vstu.dean.core.models;

import by.vstu.dean.core.enums.EStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * Абстрактный базовый объект базы данных.
 */
@Setter
@Getter
@MappedSuperclass
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Schema(description = "Базовый объект")
public abstract class DBBaseModel {

    /**
     * Идентификатор из источника данных деканата.
     */
    @ReadOnlyProperty
    @Schema(title = "Id из базы деканата")
    private Long sourceId;

    /**
     * Уникальный идентификатор объекта.
     */
    @Id
    @ReadOnlyProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Статус объекта.
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    @Schema(title = "Статус")
    private EStatus status;

    @Schema(title = "Дата создания записи")
    @CreatedDate
    @JsonIgnore
    private LocalDateTime created;

    @Schema(title = "Дата обновления записи")
    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime updated;
}
