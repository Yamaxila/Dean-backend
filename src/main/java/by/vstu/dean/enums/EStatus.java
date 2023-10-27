package by.vstu.dean.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;
import java.util.List;

/**
 * Перечисление, реализующее статусы объектов.
 */
@ApiModel(description = "Enum реализующий статус объекта")
public enum EStatus implements BaseEnum<EStatus> {

    @ApiModelProperty(notes = "Активный статус")
    ACTIVE(0),

    @ApiModelProperty(notes = "Удаленный статус")
    DELETED(1),

    @ApiModelProperty(notes = "Неизвестный статус")
    UNKNOWN(-1);

    /**
     * Конструктор с параметром.
     *
     * @param ignoredId Идентификатор статуса
     */
    EStatus(int ignoredId) {
    }

    @Override
    public List<EStatus> getValues() {
        return Arrays.stream(EStatus.values()).toList();
    }
}
