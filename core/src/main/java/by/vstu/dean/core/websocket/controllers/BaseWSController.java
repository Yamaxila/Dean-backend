package by.vstu.dean.core.websocket.controllers;

import by.vstu.dean.core.anotations.WebSocketTopic;
import by.vstu.dean.core.controllers.ControllerBaseLogic;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.core.dto.PublicDTO;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.core.trowable.BadRequestException;
import by.vstu.dean.core.utils.NumberUtils;
import by.vstu.dean.core.websocket.WSControllerManager;
import by.vstu.dean.core.websocket.WSListener;
import by.vstu.dean.core.websocket.enums.EPacketType;
import by.vstu.dean.core.websocket.enums.EPayloadType;
import by.vstu.dean.core.websocket.packets.BaseWSPacket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

import java.util.List;

/**
 * Базовый контроллер для WebSocket
 *
 * @param <D> DTO
 * @param <O> Модель
 * @param <M> Маппер
 * @param <R> Репозиторий
 * @param <S> Сервис
 */
@Slf4j
public abstract class BaseWSController<D extends BaseDTO, O extends DBBaseModel, M extends BaseMapperInterface<D, O>, R extends DBBaseModelRepository<O>, S extends BaseService<O, R>>
        extends ControllerBaseLogic<D, O, M, R, S> implements WSListener {

    //Я не знаю как это назвать. Используем для отправки сообщений через WebSocket
    protected final SimpMessagingTemplate messagingTemplate;
    //Менеджер контроллеров
    protected final WSControllerManager controllerManager;

    /**
     * Конструктор
     *
     * @param messagingTemplate - SimpMessagingTemplate
     * @param service           - Сервис (S)
     * @param mapper            - Маппер (M)
     * @param controllerManager - Менеджер контроллеров
     */
    public BaseWSController(SimpMessagingTemplate messagingTemplate, S service, M mapper, WSControllerManager controllerManager) {
        super(service, mapper);
        this.messagingTemplate = messagingTemplate;
        this.controllerManager = controllerManager;
        this.initClass();
    }

    /**
     * Инициализирует класс.
     * Регистрирует класс в менеджере контроллеров
     */
    private void initClass() {
        if (this.controllerManager == null)
            throw new RuntimeException("Topic manager is null!");

        //Получаем аннотацию у текущего класса
        WebSocketTopic websocketTopic = AnnotationUtils.findAnnotation(this.getClass(), WebSocketTopic.class);

        if (websocketTopic == null)
            throw new RuntimeException("Class " + this.getClass().getSimpleName() + " not annotated with WebsocketTopic annotation!");
        //Регистрируемся
        this.controllerManager.registerController(websocketTopic.value(), this);
        this.controllerManager.registerClass(websocketTopic.dtoClass(), this);
        this.controllerManager.registerClass(websocketTopic.modelClass(), this);

        log.info("Registered {}", this.getClass().getSimpleName());
    }

    /**
     * Получает данные по id через WebSocket
     *
     * @param packet   - Пакет, имеющий тип примитива и id в payload
     * @param accessor - Header запроса
     */
    @MessageMapping("/getById")
    public void getById(BaseWSPacket packet, StompHeaderAccessor accessor) {
        //Проверяем пакет на валидность
        if (packet == null || !packet.getPayloadType().equals(EPayloadType.PRIMITIVE)) {
            log.error("getById() Got bad packet {}", packet);
            throw new BadRequestException("Got bad packet");
        }

        //Получаем id из пакета
        Long id = NumberUtils.parseLong(packet.getPayload());

        //Проверяем id
        if (id == null || id == Long.MIN_VALUE) {
            log.error("getById() Got bad packet {}", packet);
            throw new BadRequestException("Got bad packet");
        }
        //Отправляем данные
        //Здесь EPayloadType - OBJECT, т.к. объект один
        this.sendUpdate(accessor, new BaseWSPacket(EPacketType.GET, EPayloadType.OBJECT, this.rawGetById(id), accessor != null ? accessor.getSessionId() : null));
    }

    /**
     * Получает все DTO из базы
     *
     * @param accessor - Header запроса
     */
    @MessageMapping("/getAll")
    public void getAll(StompHeaderAccessor accessor) {
        this.sendUpdate(accessor, new BaseWSPacket(EPacketType.GET, EPayloadType.OBJECT_ARRAY, this.rawGetAll(), accessor != null ? accessor.getSessionId() : null));
    }

    /**
     * Получает все DTO из базы
     *
     * @param packet   - Пакет, содержащий boolean
     * @param accessor - Header запроса
     */
    @MessageMapping("/getAllActive")
    public void getAllActive(BaseWSPacket packet, StompHeaderAccessor accessor) {
        //Проводим валидацию пакета
        if (packet != null && !packet.getPayloadType().equals(EPayloadType.PRIMITIVE)) {
            log.error("getAllActive() Got bad packet {}", packet);
            throw new BadRequestException("Got bad packet");
        }

        boolean is = packet == null || Boolean.parseBoolean((String) packet.getPayload());

        this.sendUpdate(accessor, new BaseWSPacket(EPacketType.GET, EPayloadType.OBJECT_ARRAY, this.rawGetAllActive(is), accessor != null ? accessor.getSessionId() : null));
    }

    protected void sendUpdate(StompHeaderAccessor accessor, Object data) {
        this.sendUpdate(accessor, data, EPacketType.UNKNOWN);
    }
    /**
     * Метод для отправки данных клиенту/клиентам.
     *
     * @param accessor - Header пакета. Может быть null.
     * @param data     - Данные, которые нужно отправить.
     */
    protected void sendUpdate(StompHeaderAccessor accessor, Object data, EPacketType packetType) {
        //Получаем аннотацию текущего класс
        WebSocketTopic topic = AnnotationUtils.findAnnotation(this.getClass(), WebSocketTopic.class);

        if (topic == null) {
            log.error("{} does not have WebSocketTopic annotation", this.getClass().getSimpleName());
            return;
        }

        //Строим ссылку для отправки данных
        String destination = topic.value();
        if (!destination.endsWith("/"))
            destination += "/";
        destination += "updates";

        //Если мы уже собрали пакет, то не нужно пытаться собрать его снова
        if (data instanceof BaseWSPacket p) {
            this.messagingTemplate.convertAndSend(destination, p);
            return;
        }

        EPayloadType type;
        Object out = data;
        //Применяем маппер и указываем тип данных.
        if (data instanceof List<?>) {
            type = EPayloadType.OBJECT_ARRAY;
            out = this.mapper.toDto((List<O>) data);
        } else if (data instanceof PublicDTO) {
            type = EPayloadType.OBJECT;
            out = (D) data;
        } else if (data instanceof DBBaseModel
        ) {
            type = EPayloadType.OBJECT;
            out = this.mapper.toDto((O) data);
        } else if (data instanceof Long
                || data instanceof Integer
                || data instanceof Short
                || data instanceof Byte
                || data instanceof Float
                || data instanceof Double
                || data instanceof Character
                || data instanceof String
                || data instanceof Boolean
        )
            type = EPayloadType.PRIMITIVE;
        else
            type = EPayloadType.UNKNOWN;

        log.info("Sending payload {} to {}", out, destination);
        //Отправляем данные
        this.messagingTemplate.convertAndSend(destination, new BaseWSPacket(packetType, type, out, accessor != null ? accessor.getSessionId() : "*"));
    }

    @Override
    public void onCreate(Object data) {
        this.sendUpdate(null, data, EPacketType.CREATE);
    }

    @Override
    public void onUpdate(Object data) {
        this.sendUpdate(null, data, EPacketType.UPDATE);
    }

    @Override
    public void onDelete(Object data) {
        this.sendUpdate(null, data, EPacketType.DELETE);
    }

}
