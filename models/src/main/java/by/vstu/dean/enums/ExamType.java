package by.vstu.dean.enums;

import by.vstu.dean.core.enums.BaseEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;
import java.util.List;

/**
 * Перечисление, реализующее типы экзаменов.
 */
@ApiModel(description = "Enum реализующий тип экзамена")
public enum ExamType implements BaseEnum<ExamType> {

    @ApiModelProperty(notes = "Экзамен")
    EXAM(0),

    @ApiModelProperty(notes = "Зачет")
    TEST(1),

    @ApiModelProperty(notes = "Неизвестный тип экзамена")
    UNKNOWN(-1);

    /**
     * Конструктор с параметром.
     *
     * @param ignoredId Идентификатор типа экзамена
     */
    ExamType(int ignoredId) {
    }

    @Override
    public List<ExamType> getValues() {
        return Arrays.stream(ExamType.values()).toList();
    }
}
