package by.vstu.dean.core.models;

import by.vstu.dean.core.enums.EStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
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
@ApiModel(description = "Базовый объект")
public abstract class DBBaseModel {

    /**
     * Идентификатор из источника данных деканата.
     */
    @ReadOnlyProperty
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "Id из базы деканата")
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
    @ApiModelProperty(notes = "Статус")
    private EStatus status;

    @ApiModelProperty(notes = "Дата создания записи")
    @CreatedDate
    @JsonIgnore
    private LocalDateTime created;

    @ApiModelProperty(notes = "Дата обновления записи")
    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime updated;
}
