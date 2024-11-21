package by.vstu.migrate.v1.enums;

import by.vstu.dean.core.enums.EStatus;

import java.util.Arrays;
import java.util.List;

/**
 * Перечисление, реализующее статусы объектов.
 */
public enum V1EStatus implements V1BaseEnum<V1EStatus> {

    ACTIVE(0),

    DELETED(1),

    INACTIVE(2),

    UNKNOWN(-1);

    /**
     * Конструктор с параметром.
     *
     * @param ignoredId Идентификатор статуса
     */
    V1EStatus(int ignoredId) {
    }

    @Override
    public List<V1EStatus> getValues() {
        return Arrays.stream(V1EStatus.values()).toList();
    }

    public EStatus map() {
        return switch (this) {
            case ACTIVE -> EStatus.ACTIVE;
            case DELETED -> EStatus.DELETED;
            case INACTIVE -> EStatus.BROKEN;
            case UNKNOWN -> EStatus.UNKNOWN;
        };
    }

    public static EStatus map(V1EStatus v1EStatus) {
        return switch (v1EStatus) {
            case ACTIVE -> EStatus.ACTIVE;
            case DELETED -> EStatus.DELETED;
            case INACTIVE -> EStatus.BROKEN;
            case UNKNOWN -> EStatus.UNKNOWN;
        };
    }
}
