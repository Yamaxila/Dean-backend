package by.vstu.dean.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "enum реализующий тип экзамена")
public enum ExamType {

    @ApiModelProperty(notes = "Экзамен")
    EXAM(0),
    @ApiModelProperty(notes = "Зачет")
    TEST(1),
    @ApiModelProperty(notes = "Неизвестно")
    UNKNOWN(-1);

    final int id;

    ExamType(int id) {
        this.id = id;
    }

}
