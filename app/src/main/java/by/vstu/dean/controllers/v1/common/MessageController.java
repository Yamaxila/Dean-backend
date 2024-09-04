package by.vstu.dean.controllers.v1.common;

import by.vstu.dean.core.controllers.BaseController;
import by.vstu.dean.dto.v1.MessageDTO;
import by.vstu.dean.mapper.v1.MessageMapper;
import by.vstu.dean.models.internal.MessageModel;
import by.vstu.dean.repo.MessageModelRepository;
import by.vstu.dean.services.MessageService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/messages/")
@Tag(name = "Messages", description = "Сообщения")
public class MessageController extends BaseController<MessageDTO, MessageModel, MessageMapper, MessageModelRepository, MessageService> {

    public MessageController(MessageService service, MessageMapper mapper) {
        super(service, mapper);
    }
}
