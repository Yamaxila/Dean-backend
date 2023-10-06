package by.vstu.dean.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Перечисление, реализующее типы обучения.
 */
@ApiModel(description = "Enum реализующий тип обучения")
public enum EEducationType {

    @ApiModelProperty(notes = "Дневное обучение")
    DAYTIME(0),

    @ApiModelProperty(notes = "Заочное обучение")
    EXTRAMURAL(1),

    @ApiModelProperty(notes = "Неизвестный тип обучения")
    UNKNOWN(-1);

    private final int id;

    /**
     * Конструктор с параметром.
     *
     * @param id Идентификатор типа обучения
     */
    EEducationType(int id) {
        this.id = id;
    }
}
