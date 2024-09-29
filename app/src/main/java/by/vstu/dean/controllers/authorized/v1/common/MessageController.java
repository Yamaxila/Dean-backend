package by.vstu.dean.controllers.authorized.v1.common;

import by.vstu.dean.core.controllers.BaseController;
import by.vstu.dean.dto.v1.MessageDTO;
import by.vstu.dean.mapper.v1.MessageMapper;
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
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/messages/")
@Tag(name = "Messages", description = "Сообщения")
public class MessageController extends BaseController<MessageDTO, MessageModel, MessageMapper, MessageModelRepository, MessageService> {

    /**
     * Контроллер для работы с объектами {@link MessageModel}
     */
    public MessageController(MessageService service, MessageMapper mapper) {
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
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
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
    public ResponseEntity<List<MessageDTO>> getAllByInactiveFlag(Boolean inactive) {
        List<MessageModel> models = this.service.getAllByFlag(inactive);

        if (models == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

        List<MessageDTO> dtos = this.mapper.toDto(models);

        if (dtos == null)
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);


        return new ResponseEntity<>(dtos, dtos.isEmpty() ? HttpStatus.NOT_FOUND : HttpStatus.OK);
    }
}
