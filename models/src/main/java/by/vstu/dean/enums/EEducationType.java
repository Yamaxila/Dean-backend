package by.vstu.dean.enums;

import by.vstu.dean.core.enums.BaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;
import java.util.List;

/**
 * Перечисление, реализующее типы обучения.
 */
@Schema(description = "Enum реализующий тип обучения")
public enum EEducationType implements BaseEnum<EEducationType> {

    @Schema(title = "Дневное обучение")
    DAYTIME(0),

    @Schema(title = "Заочное обучение")
    EXTRAMURAL(1),

    @Schema(title = "Неизвестный тип обучения")
    UNKNOWN(-1);

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
