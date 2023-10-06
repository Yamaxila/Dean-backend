package by.vstu.dean.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Перечисление, реализующее статусы объектов.
 */
@ApiModel(description = "Enum реализующий статус объекта")
public enum EStatus {

    @ApiModelProperty(notes = "Активный статус")
    ACTIVE(0),

    @ApiModelProperty(notes = "Удаленный статус")
    DELETED(1),

    @ApiModelProperty(notes = "Неизвестный статус")
    UNKNOWN(-1);

    private final int id;

    /**
     * Конструктор с параметром.
     *
     * @param id Идентификатор статуса
     */
    EStatus(int id) {
        this.id = id;
    }
}
