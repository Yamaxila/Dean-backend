package by.vstu.dean.enums;

import by.vstu.dean.core.enums.BaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;
import java.util.List;

/**
 * Перечисление, реализующее типы обучения.
 */
@Schema(title = "Форма образования")
@SuppressWarnings("unused")
public enum EEducationType implements BaseEnum<EEducationType> {

    @Schema(title = "Неизвестный тип обучения")
    UNKNOWN(0),

    @Schema(title = "Дневное обучение")
    DAYTIME(1),

    @Schema(title = "Заочное обучение")
    EXTRAMURAL(2);

    /**
     * Конструктор с параметром.
     *
     * @param ignoredId Идентификатор типа обучения
     */
    EEducationType(int ignoredId) {
    }

    @Override
    public List<EEducationType> getValues() {
        return Arrays.stream(EEducationType.values()).toList();
    }
}
