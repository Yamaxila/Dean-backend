package by.vstu.migrate.v1;

import by.vstu.migrate.v1.enums.V1EStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
public abstract class V1DBBaseModel {

    /**
     * Идентификатор из источника данных деканата.
     */
    @ReadOnlyProperty
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
    private V1EStatus status;

    @CreatedDate
    @JsonIgnore
    private LocalDateTime created;

    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime updated;
}
