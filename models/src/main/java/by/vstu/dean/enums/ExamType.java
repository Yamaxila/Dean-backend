package by.vstu.dean.enums;

import by.vstu.dean.core.enums.BaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;
import java.util.List;

/**
 * Перечисление, реализующее типы экзаменов.
 */
@Schema(title = "Тип экзамена")
public enum ExamType implements BaseEnum<ExamType> {

    @Schema(title = "Неизвестный тип экзамена")
    UNKNOWN(0),

    @Schema(title = "Экзамен")
    EXAM(1),

    @Schema(title = "Зачет")
    TEST(2);


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
