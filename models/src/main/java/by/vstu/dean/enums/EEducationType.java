package by.vstu.dean.enums;

import by.vstu.dean.core.enums.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;
import java.util.List;

/**
 * Перечисление, реализующее типы обучения.
 */
@ApiModel(description = "Enum реализующий тип обучения")
public enum EEducationType implements BaseEnum<EEducationType> {

    @ApiModelProperty(notes = "Дневное обучение")
    DAYTIME(0),

    @ApiModelProperty(notes = "Заочное обучение")
    EXTRAMURAL(1),

    @ApiModelProperty(notes = "Неизвестный тип обучения")
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
