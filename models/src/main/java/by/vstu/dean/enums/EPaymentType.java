package by.vstu.dean.enums;

import by.vstu.dean.core.enums.BaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;
import java.util.List;

/**
 * Перечисление, реализующее типы оплаты обучения.
 */
@Schema(description = "Enum реализующий тип оплаты обучения")
public enum EPaymentType implements BaseEnum<EPaymentType> {

    @Schema(title = "Бесплатное обучение")
    NOT_PAID(0),

    @Schema(title = "Платное обучение")
    PAID(1),

    @Schema(title = "Целевое обучение")
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
