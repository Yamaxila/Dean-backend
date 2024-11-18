package by.vstu.migrate.v1.enums;

import by.vstu.dean.enums.ESemester;

import java.util.Arrays;
import java.util.List;

public enum V1ESemester implements V1BaseEnum<V1ESemester> {

    SPRING(0),
    AUTUMN(1),

    UNKNOWN(-1);
    @SuppressWarnings("unused")
    final int id;

    V1ESemester(int id) {
        this.id = id;
    }

    public ESemester map() {
        return switch (this) {
            case SPRING -> ESemester.SPRING;
            case AUTUMN -> ESemester.AUTUMN;
            case UNKNOWN -> ESemester.UNKNOWN;
        };
    }

    @Override
    public List<V1ESemester> getValues() {
        return Arrays.stream(V1ESemester.values()).toList();
    }
}
