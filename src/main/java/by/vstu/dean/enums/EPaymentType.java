package by.vstu.dean.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;
import java.util.List;

/**
 * Перечисление, реализующее типы оплаты обучения.
 */
@ApiModel(description = "Enum реализующий тип оплаты обучения")
public enum EPaymentType implements BaseEnum<EPaymentType> {

    @ApiModelProperty(notes = "Бесплатное обучение")
    NOT_PAID(0),

    @ApiModelProperty(notes = "Платное обучение")
    PAID(1),

    @ApiModelProperty(notes = "Целевое обучение")
    DIRECTIONAL(2);

    /**
     * Конструктор с параметром.
     *
     * @param ignoredId Идентификатор типа оплаты
     */
    EPaymentType(int ignoredId) {
    }


    @Override
    public List<EPaymentType> getValues() {
        return Arrays.stream(EPaymentType.values()).toList();
    }
}
