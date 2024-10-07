package by.vstu.dean.services;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.core.trowable.CronExpressionParseException;
import by.vstu.dean.core.trowable.DatabaseFetchException;
import by.vstu.dean.models.internal.MessageModel;
import by.vstu.dean.repo.MessageModelRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.javers.core.Javers;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class MessageService extends BaseService<MessageModel, MessageModelRepository> {

    public MessageService(MessageModelRepository repo, Javers javers) {
        super(repo, javers);
    }

    //Каждый день в 07:00 нужно проверить сообщения и установить флаг inactive тем,
    //что сегодня должны выполниться
    @Scheduled(cron = "0 0 7 * * *")
    @PostConstruct
    public void checkCron() {
        List<MessageModel> messages = this.getAllByFlag(true);

        if (messages == null)
            throw new DatabaseFetchException("Can't fetch messages by flag `true`");

        LocalDateTime now = LocalDateTime.now();

        List<MessageModel> temp = new ArrayList<>();

        try {

            messages.stream().filter(p -> p.getStatus().equals(EStatus.ACTIVE)).forEach(message -> {

                CronExpression cronTrigger = CronExpression.parse(message.getCronExpression());

                LocalDateTime next = cronTrigger.next(LocalDateTime.now());

                if (next == null)
                    throw new CronExpressionParseException("Cron expression is invalid: " + message.getCronExpression() + ", message id " + message.getId());

                if (next.getDayOfYear() == now.getDayOfYear()) {
                    message.setInactive(false);
                    temp.add(message);
                }

            });

            log.info("Updating internal messages");
            this.saveAll(temp);
        } catch (CronExpressionParseException e) {
            log.error("Cron expression is invalid", e);
        }
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

    /**
     * Устанавливает флаг активности сообщения на текущий момент
     *
     * @param model    - сообщение
     * @param inactive - флаг активности
     * @return сохраненное сообщение
     */
    public MessageModel setInactive(MessageModel model, Boolean inactive) {
        model.setInactive(inactive);
        model.setUpdated(LocalDateTime.now());
        return this.save(model);
    }

    /**
     * Устанавливает флаг активности сообщения по идентификатору на текущий момент
     *
     * @param messageId - идентификатор сообщения
     * @param inactive  - флаг активности
     * @return сохраненное сообщение
     */
    public MessageModel setInactive(Long messageId, Boolean inactive) {
        Optional<MessageModel> model = this.getById(messageId);

        return model
                .map(messageModel -> this.setInactive(messageModel, inactive))
                .orElseThrow(() -> new DatabaseFetchException("Can't find message with id: " + messageId));

    }

}
