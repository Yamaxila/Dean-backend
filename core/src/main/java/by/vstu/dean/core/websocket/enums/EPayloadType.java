package by.vstu.dean.core.websocket.enums;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Enum реализующий тип данных пакета WS")
public enum EPayloadType {

    @Schema(title = "Неизвестный тип")
    UNKNOWN(0),
    @Schema(title = "Объект")
    OBJECT(1),
    @Schema(title = "Массив")
    ARRAY(2),
    @Schema(title = "Массив")
    OBJECT_ARRAY(3),
    @Schema(title = "Примитив")
    PRIMITIVE(4),
    @Schema(title = "Сессия")
    SESSION(5);

    EPayloadType(int ignoredId) {
    }
}
