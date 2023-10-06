package by.vstu.dean.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Перечисление, реализующее типы оплаты обучения.
 */
@ApiModel(description = "Enum реализующий тип оплаты обучения")
public enum EPaymentType {

    @ApiModelProperty(notes = "Бесплатное обучение")
    NOT_PAID(0),

    @ApiModelProperty(notes = "Платное обучение")
    PAID(1),

    @ApiModelProperty(notes = "Целевое обучение")
    DIRECTIONAL(2);

    private final int id;

    /**
     * Конструктор с параметром.
     *
     * @param id Идентификатор типа оплаты
     */
    EPaymentType(int id) {
        this.id = id;
    }
}
