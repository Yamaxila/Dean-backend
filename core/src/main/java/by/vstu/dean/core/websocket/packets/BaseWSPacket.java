package by.vstu.dean.core.websocket.packets;

import by.vstu.dean.core.websocket.enums.EPacketType;
import by.vstu.dean.core.websocket.enums.EPayloadType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Пакет данных WS
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseWSPacket {

    //Тип пакета
    private EPacketType packetType;
    //Тип данных
    private EPayloadType payloadType;
    //Данные
    private Object payload;
    //Сессия, для которой предназначен пакет
    //UUID или "*", если пакет предназначен для всех
    private String sessionId;

}
