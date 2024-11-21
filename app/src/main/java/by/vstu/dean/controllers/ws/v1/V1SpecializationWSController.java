package by.vstu.dean.controllers.ws.v1;

import by.vstu.dean.core.anotations.WebSocketTopic;
import by.vstu.dean.core.websocket.WSControllerManager;
import by.vstu.dean.core.websocket.controllers.BaseWSController;
import by.vstu.dean.dto.v1.specs.V1SpecializationDTO;
import by.vstu.dean.mapper.v1.V1SpecializationMapper;
import by.vstu.dean.models.specs.SpecializationModel;
import by.vstu.dean.repo.SpecializationModelRepository;
import by.vstu.dean.services.SpecializationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@WebSocketTopic(value = "/topic/spez/", dtoClass = V1SpecializationDTO.class, modelClass = SpecializationModel.class)
@MessageMapping("/spez/")
public class V1SpecializationWSController extends BaseWSController<V1SpecializationDTO, SpecializationModel, V1SpecializationMapper, SpecializationModelRepository, SpecializationService> {
    /**
     * Конструктор
     *
     * @param messagingTemplate - SimpMessagingTemplate
     * @param service           - Сервис (S)
     * @param mapper            - Маппер (M)
     * @param controllerManager - Менеджер контроллеров
     */
    public V1SpecializationWSController(SimpMessagingTemplate messagingTemplate, SpecializationService service, V1SpecializationMapper mapper, WSControllerManager controllerManager) {
        super(messagingTemplate, service, mapper, controllerManager);
    }
}
