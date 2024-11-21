package by.vstu.dean.core.websocket.controllers;

import by.vstu.dean.core.trowable.BadRequestException;
import by.vstu.dean.core.websocket.enums.EPacketType;
import by.vstu.dean.core.websocket.enums.EPayloadType;
import by.vstu.dean.core.websocket.packets.BaseWSPacket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Controller;

/**
 * Контроллер WebSocket для получения sessionId клиентом.
 */
@Controller
@MessageMapping("/session/")
@Slf4j
public class WSSessionController {

    /**
     * Метод для получения сессии
     *
     * @param packet   - Пакет с типом SESSION
     * @param accessor - Header запроса
     * @return сессию в виде пакета
     */
    @MessageMapping("/get")
    @SendTo("/topic/session")
    public BaseWSPacket getSession(BaseWSPacket packet, StompHeaderAccessor accessor) {

        if (packet == null || !packet.getPayloadType().equals(EPayloadType.SESSION)) {
            log.error("Got invalid session packet {}", packet);
            throw new BadRequestException("Invalid packet");
        }

        if (accessor == null || accessor.getSessionId() == null) {
            log.error("Got invalid accessor {}", accessor);
            throw new BadRequestException("Invalid accessor");
        }

        return new BaseWSPacket(EPacketType.GET, EPayloadType.SESSION, null, accessor.getSessionId());

    }

}
