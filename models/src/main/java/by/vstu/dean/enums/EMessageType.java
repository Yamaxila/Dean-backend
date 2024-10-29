package by.vstu.dean.enums;

import by.vstu.dean.core.enums.BaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;
import java.util.List;

@Schema(title = "Тип внутреннего сообщения")
public enum EMessageType implements BaseEnum<EMessageType> {

    UNKNOWN(0),

    INFO(1),
    WARNING(2),
    ERROR(3),
    SCHEDULED(4);


    EMessageType(int ignoredId) {
    }

    public List<EMessageType> getValues() {
        return Arrays.stream(EMessageType.values()).toList();
    }

}
