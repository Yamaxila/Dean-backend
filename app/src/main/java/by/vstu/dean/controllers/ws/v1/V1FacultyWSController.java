package by.vstu.dean.controllers.ws.v1;

import by.vstu.dean.core.anotations.WebSocketTopic;
import by.vstu.dean.core.websocket.WSControllerManager;
import by.vstu.dean.core.websocket.controllers.BaseWSController;
import by.vstu.dean.dto.v1.V1FacultyDTO;
import by.vstu.dean.mapper.v1.V1FacultyMapper;
import by.vstu.dean.models.FacultyModel;
import by.vstu.dean.repo.FacultyModelRepository;
import by.vstu.dean.services.FacultyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@WebSocketTopic(value = "/topic/faculty/", dtoClass = V1FacultyDTO.class, modelClass = FacultyModel.class)
@MessageMapping("/faculty/")
public class V1FacultyWSController extends BaseWSController<V1FacultyDTO, FacultyModel, V1FacultyMapper, FacultyModelRepository, FacultyService> {
    /**
     * Конструктор
     *
     * @param messagingTemplate - SimpMessagingTemplate
     * @param service           - Сервис (S)
     * @param mapper            - Маппер (M)
     * @param controllerManager - Менеджер контроллеров
     */
    public V1FacultyWSController(SimpMessagingTemplate messagingTemplate, FacultyService service, V1FacultyMapper mapper, WSControllerManager controllerManager) {
        super(messagingTemplate, service, mapper, controllerManager);
    }
}
