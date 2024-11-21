package by.vstu.dean.enums;

import by.vstu.dean.core.enums.BaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;
import java.util.List;

@Schema(title = "Общежитие")
public enum EHostel implements BaseEnum<EHostel> {

    UNKNOWN(0),
    HOSTEL_2(1),
    HOSTEL_3(2);

    public final int id;

    EHostel(int id) {
        this.id = id;
    }

    @SuppressWarnings("unused")
    public static EHostel valueOf(int id) {
        return Arrays.stream(values()).filter(p -> p.id == id).findFirst().orElse(UNKNOWN);
    }

    @Override
    public List<EHostel> getValues() {
        return Arrays.stream(EHostel.values()).toList();
    }
}
