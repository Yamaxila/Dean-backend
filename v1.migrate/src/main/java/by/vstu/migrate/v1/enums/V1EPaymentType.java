package by.vstu.migrate.v1.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Перечисление, реализующее типы оплаты обучения.
 */
public enum V1EPaymentType implements V1BaseEnum<V1EPaymentType> {

    NOT_PAID(0),

    PAID(1),

    DIRECTIONAL(2);

    /**
     * Конструктор с параметром.
     *
     * @param ignoredId Идентификатор типа оплаты
     */
    V1EPaymentType(int ignoredId) {
    }


    @Override
    public List<V1EPaymentType> getValues() {
        return Arrays.stream(V1EPaymentType.values()).toList();
    }
}
