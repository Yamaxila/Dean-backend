package by.vstu.dean.enums;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Перечисление, реализующее типы экзаменов.
 */
@ApiModel(description = "Enum реализующий тип экзамена")
public enum ExamType {

    @ApiModelProperty(notes = "Экзамен")
    EXAM(0),

    @ApiModelProperty(notes = "Зачет")
    TEST(1),

    @ApiModelProperty(notes = "Неизвестный тип экзамена")
    UNKNOWN(-1);

    private final int id;

    /**
     * Конструктор с параметром.
     *
     * @param id Идентификатор типа экзамена
     */
    ExamType(int id) {
        this.id = id;
    }
}
