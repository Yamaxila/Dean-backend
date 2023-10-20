package by.vstu.dean.enums;

import lombok.Getter;

import java.util.Arrays;

public enum EFrame {

    FIRST(1),
    SECOND(2),
    FOURTH(4),
    FIFTH(5),

    UNKNOWN(-1);

    @Getter
    final int id;

    EFrame(int id) {
        this.id = id;
    }

    public static EFrame valueOf(int id) {
        return Arrays.stream(values()).filter(p -> p.id == id).findFirst().orElse(UNKNOWN);
    }
}
