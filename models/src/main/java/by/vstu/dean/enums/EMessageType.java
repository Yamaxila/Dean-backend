package by.vstu.dean.enums;

import by.vstu.dean.core.enums.BaseEnum;
import io.swagger.annotations.ApiModel;

import java.util.Arrays;
import java.util.List;

@ApiModel(description = "Enum реализующий тип внутреннего сообщения")
public enum EMessageType implements BaseEnum<EMessageType> {

    INFO(0),
    WARNING(1),
    ERROR(2),
    SCHEDULED(3),

    UNKNOWN(-1);

    EMessageType(int ignoredId) {
    }

    public List<EMessageType> getValues() {
        return Arrays.stream(EMessageType.values()).toList();
    }

}
