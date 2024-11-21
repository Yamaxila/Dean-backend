package by.vstu.dean.controllers.authorized.v1.read.common;

import by.vstu.dean.core.controllers.BaseController;
import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.trowable.CronExpressionParseException;
import by.vstu.dean.core.trowable.DatabaseFetchException;
import by.vstu.dean.core.trowable.MappingException;
import by.vstu.dean.dto.v1.V1MessageDTO;
import by.vstu.dean.mapper.v1.V1MessageMapper;
import by.vstu.dean.models.internal.MessageModel;
import by.vstu.dean.repo.MessageModelRepository;
import by.vstu.dean.services.MessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/messages")
@Tag(name = "Messages", description = "Сообщения")
@PreAuthorize("hasAnyAuthority('ROLE_SERVICE', 'ROLE_METHODIST')")
public class V1ReadMessageController extends BaseController<V1MessageDTO, MessageModel, V1MessageMapper, MessageModelRepository, MessageService> {

    /**
     * Контроллер для работы с объектами {@link MessageModel}
     */
    public V1ReadMessageController(MessageService service, V1MessageMapper mapper) {
        super(service, mapper);
    }

    /**
     * Получает все объекты по флагу inactive из базы данных.
     *
     * @return Список объектов по флагу
     */
    @RequestMapping(value = "/by_flag",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Operation(method = "getAllByInactiveFlag", description = "Отправляет все объекты из базы по флагу \"inactive\"",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Объекты найдены."),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права read у клиента.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "404", description = "Объекты не найдены", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"read", "ROLE_USER", "ROLE_ADMIN"})
    )
    public ResponseEntity<List<V1MessageDTO>> getAllByInactiveFlag(Boolean inactive) {
        List<MessageModel> models = this.service.getAllByFlag(inactive);

        if (models == null)
            throw new DatabaseFetchException("Can't fetch data from database");

        List<V1MessageDTO> dtos = this.mapper.toDto(models);

        if (dtos == null)
            throw new MappingException("Can't map messages to DTO");


        return new ResponseEntity<>(dtos, dtos.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }

    /**
     * Получает все объекты по флагу inactive из базы данных.
     *
     * @return Список объектов по флагу
     */
    @RequestMapping(value = "/current",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Operation(method = "getCurrentMessages", description = "Отправляет все объекты из базы, что должны быть отправлены сегодня",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Объекты найдены."),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права read у клиента.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "404", description = "Объекты не найдены", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"read", "ROLE_USER", "ROLE_ADMIN"})
    )
    public ResponseEntity<List<V1MessageDTO>> getCurrentMessages() {

        List<MessageModel> messages = this.service.getAllByFlag(false);

        if (messages == null)
            throw new DatabaseFetchException("Cannot fetch messages by flag `false`");

        if (messages.isEmpty())
            return ResponseEntity.ok(new ArrayList<>());

        LocalDateTime now = LocalDateTime.now();

        List<MessageModel> temp = new ArrayList<>();

        messages.stream().filter(p -> p.getStatus().equals(EStatus.ACTIVE) && p.getCronExpression() != null).forEach(message -> {

            CronExpression cronTrigger = CronExpression.parse(message.getCronExpression());

            LocalDateTime next = cronTrigger.next(LocalDateTime.now());

            if (next == null)
                throw new CronExpressionParseException("Cron expression is invalid: " + message.getCronExpression() + ", message id " + message.getId());

            //Нам нужно проверить, сегодня ли должно выполниться сообщение и возможно, его пропустили
            if (now.isAfter(next) || next.getDayOfYear() == now.getDayOfYear())
                temp.add(message);

        });

        List<V1MessageDTO> dtos = this.mapper.toDto(temp);

        if (dtos == null)
            throw new MappingException("Can't map messages to DTO");

        return ResponseEntity.ok(dtos);
    }

    /**
     * Устанавливает флаг активности сообщения.
     *
     * @return Сохраненное сообщение
     */
    @RequestMapping(value = "/flag",
            produces = {"application/json"},
            method = RequestMethod.POST)
    @Operation(method = "setInactive", description = "Устанавливает флаг активности сообщения",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Объект сохранен."),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права read у клиента.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "404", description = "Объект не найдены", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"write", "ROLE_USER", "ROLE_ADMIN"})
    )
    private MessageModel setInactive(Long messageId, Boolean inactive) {
        return this.service.setInactive(messageId, inactive);
    }

}
