package by.vstu.dean.enums;

import java.util.Arrays;
import java.util.List;

public enum ESemester implements BaseEnum<ESemester>{

    SPRING(0),
    AUTUMN(1),

    UNKNOWN(-1);
    @SuppressWarnings("unused")
    final int id;
    ESemester(int id) {
        this.id = id;
    }

    @Override
    public List<ESemester> getValues() {
        return Arrays.stream(ESemester.values()).toList();
    }
}
