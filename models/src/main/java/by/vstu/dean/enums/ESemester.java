package by.vstu.dean.enums;

import by.vstu.dean.core.enums.BaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;
import java.util.List;

@Schema(title = "Семестр")
public enum ESemester implements BaseEnum<ESemester> {

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
