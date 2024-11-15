package by.vstu.migrate.v1.enums;

import by.vstu.dean.enums.EHostelRoomType;

import java.util.Arrays;
import java.util.List;

public enum V1EHostelRoomType implements V1BaseEnum<V1EHostelRoomType> {

    LITTLE(0), BIG(1), UNKNOWN(-1);

    V1EHostelRoomType(int ignoredId) {
    }

    public EHostelRoomType map() {
        return switch (this) {
            case LITTLE -> EHostelRoomType.LITTLE;
            case BIG -> EHostelRoomType.BIG;
            case UNKNOWN -> EHostelRoomType.UNKNOWN;
        };
    }

    @Override
    public List<V1EHostelRoomType> getValues() {
        return Arrays.stream(V1EHostelRoomType.values()).toList();
    }
}
