package by.vstu.dean.enums;

import by.vstu.dean.core.enums.BaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;
import java.util.List;

/**
 * Перечисление, реализующее типы оплаты обучения.
 */
@Schema(title = "Тип оплаты обучения")
@SuppressWarnings("unused")
public enum EPaymentType implements BaseEnum<EPaymentType> {

    @Schema(title = "Неизвестно")
    UNKNOWN(-1),

    @Schema(title = "Бесплатное обучение")
    NOT_PAID(1),

    @Schema(title = "Платное обучение")
    PAID(2),

    @Schema(title = "Целевое обучение")
    DIRECTIONAL(3);


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
