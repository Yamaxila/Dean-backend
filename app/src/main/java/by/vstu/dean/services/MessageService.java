package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.models.internal.MessageModel;
import by.vstu.dean.repo.MessageModelRepository;
import org.springframework.stereotype.Service;

@Service
public class MessageService extends BaseService<MessageModel, MessageModelRepository> {
    public MessageService(MessageModelRepository repo) {
        super(repo);
    }
}
