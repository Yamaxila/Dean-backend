package by.vstu.dean.enums;

import java.util.Arrays;
import java.util.List;

public enum EHostelRoomType implements BaseEnum<EHostelRoomType> {

    LITTLE(0), BIG(1), UNKNOWN(-1);

    EHostelRoomType(int ignoredId) {
    }


    @Override
    public List<EHostelRoomType> getValues() {
        return Arrays.stream(EHostelRoomType.values()).toList();
    }
}
