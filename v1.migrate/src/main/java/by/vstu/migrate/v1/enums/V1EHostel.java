package by.vstu.migrate.v1.enums;

import by.vstu.dean.enums.EHostel;

import java.util.Arrays;
import java.util.List;

public enum V1EHostel implements V1BaseEnum<V1EHostel> {

    HOSTEL_2(1), HOSTEL_3(2), UNKNOWN(-1);

    public final int id;

    V1EHostel(int id) {
        this.id = id;
    }

    public EHostel map() {
        return switch (this) {
            case HOSTEL_2 -> EHostel.HOSTEL_2;
            case HOSTEL_3 -> EHostel.HOSTEL_3;
            case UNKNOWN -> EHostel.UNKNOWN;
        };
    }

    public static V1EHostel valueOf(int id) {
        return Arrays.stream(values()).filter(p -> p.id == id).findFirst().orElse(UNKNOWN);
    }

    @Override
    public List<V1EHostel> getValues() {
        return Arrays.stream(V1EHostel.values()).toList();
    }
}
