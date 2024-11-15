package by.vstu.migrate.v1.enums;

import by.vstu.dean.enums.ExamType;

import java.util.Arrays;
import java.util.List;

/**
 * Перечисление, реализующее типы экзаменов.
 */
public enum V1ExamType implements V1BaseEnum<V1ExamType> {

    EXAM(0),

    TEST(1),

    UNKNOWN(-1);

    /**
     * Конструктор с параметром.
     *
     * @param ignoredId Идентификатор типа экзамена
     */
    V1ExamType(int ignoredId) {
    }

    public ExamType map() {
        return switch (this) {
            case EXAM -> ExamType.EXAM;
            case TEST -> ExamType.TEST;
            case UNKNOWN -> ExamType.UNKNOWN;
        };
    }

    @Override
    public List<V1ExamType> getValues() {
        return Arrays.stream(V1ExamType.values()).toList();
    }
}
