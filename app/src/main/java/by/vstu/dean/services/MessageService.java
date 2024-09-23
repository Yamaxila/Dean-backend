package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.models.internal.MessageModel;
import by.vstu.dean.repo.MessageModelRepository;
import org.javers.core.Javers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService extends BaseService<MessageModel, MessageModelRepository> {
    public MessageService(MessageModelRepository repo, Javers javers) {
        super(repo, javers);
    }


    /**
     * Возвращает все активные сообщения НЕ по статусу, а флагу @link{MessageModel#getInactive()}
     *
     * @param inactive - статус активности. False - запись активна, True - запись неактивна.
     *                 параметр по умолчанию - false.
     * @return Список сообщений по статусу
     */
    public List<MessageModel> getAllByFlag(Boolean inactive) {
        if (inactive == null)
            inactive = false;

        return super.getRepo().findAllByInactiveIs(inactive);
    }
}
