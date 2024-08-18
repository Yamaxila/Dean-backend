package by.vstu.dean.enums;

import by.vstu.dean.core.enums.BaseEnum;

import java.util.Arrays;
import java.util.List;

public enum EHostel implements BaseEnum<EHostel> {

    HOSTEL_2(1), HOSTEL_3(2), UNKNOWN(-1);

    public final int id;

    EHostel(int id) {
        this.id = id;
    }

    public static EHostel valueOf(int id) {
        return Arrays.stream(values()).filter(p -> p.id == id).findFirst().orElse(UNKNOWN);
    }

    @Override
    public List<EHostel> getValues() {
        return Arrays.stream(EHostel.values()).toList();
    }
}
