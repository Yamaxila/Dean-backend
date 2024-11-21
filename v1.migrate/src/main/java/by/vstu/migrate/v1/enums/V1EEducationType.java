package by.vstu.migrate.v1.enums;

import java.util.Arrays;
import java.util.List;

/**
 * Перечисление, реализующее типы обучения.
 */
public enum V1EEducationType implements V1BaseEnum<V1EEducationType> {

    DAYTIME(0),

    EXTRAMURAL(1),

    UNKNOWN(-1);

    /**
     * Конструктор с параметром.
     *
     * @param ignoredId Идентификатор типа обучения
     */
    V1EEducationType(int ignoredId) {
    }

    @Override
    public List<V1EEducationType> getValues() {
        return Arrays.stream(V1EEducationType.values()).toList();
    }
}
