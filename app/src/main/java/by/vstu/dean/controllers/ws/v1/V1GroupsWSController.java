package by.vstu.dean.controllers.ws.v1;

import by.vstu.dean.core.anotations.WebSocketTopic;
import by.vstu.dean.core.websocket.WSControllerManager;
import by.vstu.dean.core.websocket.controllers.BaseWSController;
import by.vstu.dean.dto.v1.students.V1GroupDTO;
import by.vstu.dean.mapper.v1.V1GroupMapper;
import by.vstu.dean.models.students.GroupModel;
import by.vstu.dean.repo.GroupModelRepository;
import by.vstu.dean.services.GroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@WebSocketTopic(value = "/topic/groups/", dtoClass = V1GroupDTO.class, modelClass = GroupModel.class)
@MessageMapping("/groups/")
public class V1GroupsWSController extends BaseWSController<V1GroupDTO, GroupModel, V1GroupMapper, GroupModelRepository, GroupService> {
    /**
     * Конструктор
     *
     * @param messagingTemplate - SimpMessagingTemplate
     * @param service           - Сервис (S)
     * @param mapper            - Маппер (M)
     * @param controllerManager - Менеджер контроллеров
     */
    public V1GroupsWSController(SimpMessagingTemplate messagingTemplate, GroupService service, V1GroupMapper mapper, WSControllerManager controllerManager) {
        super(messagingTemplate, service, mapper, controllerManager);
    }
}
