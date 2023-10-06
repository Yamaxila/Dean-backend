package by.vstu.dean.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "enum реализующий тип обучения")
public enum EPaymentType {

    @ApiModelProperty(notes = "Бесплатно")
    NOT_PAID(0),
    @ApiModelProperty(notes = "Платно")
    PAID(1),
    @ApiModelProperty(notes = "Целевое")
    DIRECTIONAL(2);

    int id;

    EPaymentType(int id) {
        this.id = id;
    }
}
