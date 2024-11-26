package by.vstu.dean.enums;

import by.vstu.dean.core.enums.BaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Schema(title = "Семестр")
@Getter
public enum ESemester implements BaseEnum<ESemester> {

    UNKNOWN(0, ""),

    SPRING(1, "Весенний"),
    AUTUMN(2, "Осенний");

    final int id;
    final String name;

    ESemester(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public List<ESemester> getValues() {
        return Arrays.stream(ESemester.values()).toList();
    }

}
