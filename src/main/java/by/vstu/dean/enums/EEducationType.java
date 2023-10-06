package by.vstu.dean.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "enum реализующий тип обучения")
public enum EEducationType {

    @ApiModelProperty(notes = "Дневное")
    DAYTIME(0),
    @ApiModelProperty(notes = "Заочное")
    EXTRAMURAL(1),
    @ApiModelProperty(notes = "Неизвестно")
    UNKNOWN(-1);

    final int id;

    EEducationType(int id) {
        this.id = id;
    }
}
