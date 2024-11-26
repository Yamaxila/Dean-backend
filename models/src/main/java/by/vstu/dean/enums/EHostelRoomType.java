package by.vstu.dean.enums;

import by.vstu.dean.core.enums.BaseEnum;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;
import java.util.List;

@Schema(title = "Тип комнаты общежития")
public enum EHostelRoomType implements BaseEnum<EHostelRoomType> {

    UNKNOWN(0),
    LITTLE(1),
    BIG(2);


    EHostelRoomType(int ignoredId) {
    }


    @Override
    public List<EHostelRoomType> getValues() {
        return Arrays.stream(EHostelRoomType.values()).toList();
    }
}
