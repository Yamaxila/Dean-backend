package by.vstu.dean.future;

import by.vstu.dean.enums.EStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

/**
 * Абстрактный базовый объект базы данных.
 */
@Data
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

}