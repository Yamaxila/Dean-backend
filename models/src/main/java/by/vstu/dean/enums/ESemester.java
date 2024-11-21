package by.vstu.dean.enums;

import by.vstu.dean.core.enums.BaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Schema(title = "Семестр")
@Getter
public enum ESemester implements BaseEnum<ESemester> {

    UNKNOWN(0),

    SPRING(1),
    AUTUMN(2);

    final int id;

    ESemester(int id) {
        this.id = id;
    }

    @Override
    public List<ESemester> getValues() {
        return Arrays.stream(ESemester.values()).toList();
    }
}
