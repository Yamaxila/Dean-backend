package by.vstu.dean.enums;

import by.vstu.dean.core.enums.BaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
@Schema(title = "Корпус")
public enum EFrame implements BaseEnum<EFrame> {

    FIRST(1),
    SECOND(2),
    FOURTH(4),
    FIFTH(5),

    UNKNOWN(-1);

    final int id;

    EFrame(int id) {
        this.id = id;
    }

    public static EFrame valueOf(int id) {
        return Arrays.stream(values()).filter(p -> p.id == id).findFirst().orElse(UNKNOWN);
    }

    @Override
    public List<EFrame> getValues() {
        return Arrays.stream(EFrame.values()).toList();
    }
}
