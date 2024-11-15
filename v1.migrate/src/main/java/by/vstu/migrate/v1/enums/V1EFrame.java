package by.vstu.migrate.v1.enums;

import by.vstu.dean.enums.EFrame;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum V1EFrame implements V1BaseEnum<V1EFrame> {

    FIRST(1),
    SECOND(2),
    FOURTH(4),
    FIFTH(5),

    UNKNOWN(-1);

    final int id;

    V1EFrame(int id) {
        this.id = id;
    }

    public EFrame map() {
        return switch (this) {
            case FIRST -> EFrame.FIRST;
            case SECOND -> EFrame.SECOND;
            case FOURTH -> EFrame.FOURTH;
            case FIFTH -> EFrame.FIFTH;
            case UNKNOWN -> EFrame.UNKNOWN;
        };
    }

    public static V1EFrame valueOf(int id) {
        return Arrays.stream(values()).filter(p -> p.id == id).findFirst().orElse(UNKNOWN);
    }

    @Override
    public List<V1EFrame> getValues() {
        return Arrays.stream(V1EFrame.values()).toList();
    }
}
