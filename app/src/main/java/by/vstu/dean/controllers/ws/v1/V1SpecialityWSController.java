package by.vstu.dean.controllers.ws.v1;

import by.vstu.dean.core.anotations.WebSocketTopic;
import by.vstu.dean.core.websocket.WSControllerManager;
import by.vstu.dean.core.websocket.controllers.BaseWSController;
import by.vstu.dean.dto.v1.specs.V1SpecialityDTO;
import by.vstu.dean.mapper.v1.V1SpecialityMapper;
import by.vstu.dean.models.specs.SpecialityModel;
import by.vstu.dean.repo.SpecialityModelRepository;
import by.vstu.dean.services.SpecialityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@WebSocketTopic(value = "/topic/specs/", dtoClass = V1SpecialityDTO.class, modelClass = SpecialityModel.class)
@MessageMapping("/specs/")
public class V1SpecialityWSController extends BaseWSController<V1SpecialityDTO, SpecialityModel, V1SpecialityMapper, SpecialityModelRepository, SpecialityService> {
    /**
     * Конструктор
     *
     * @param messagingTemplate - SimpMessagingTemplate
     * @param service           - Сервис (S)
     * @param mapper            - Маппер (M)
     * @param controllerManager - Менеджер контроллеров
     */
    public V1SpecialityWSController(SimpMessagingTemplate messagingTemplate, SpecialityService service, V1SpecialityMapper mapper, WSControllerManager controllerManager) {
        super(messagingTemplate, service, mapper, controllerManager);
    }
}
