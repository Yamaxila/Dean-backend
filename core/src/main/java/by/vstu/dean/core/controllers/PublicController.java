package by.vstu.dean.core.controllers;

import by.vstu.dean.core.dto.PublicDTO;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.core.services.BaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
public class PublicController<D extends PublicDTO, O extends DBBaseModel, M extends BaseMapperInterface<D, O>, R extends DBBaseModelRepository<O>, S extends BaseService<O, R>>
        extends ControllerBaseLogic<D, O, M, R, S> {

    public PublicController(S service, M mapper) {
        super(service, mapper);
    }

    /**
     * Получает все объекты из базы данных.
     *
     * @return Список объектов
     */
    @RequestMapping(value = "",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Operation(method = "getAll", description = "Отправляет все объекты из базы",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Объекты найдены."),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права read у клиента или ролей USER, ADMIN.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            }
    )
    public ResponseEntity<List<D>> getAll() {
        return ResponseEntity.ok(this.rawGetAll());
    }


    /**
     * Получает все активные объекты из базы данных.
     *
     * @return Список активных объектов
     */
    @RequestMapping(value = "/active",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Operation(method = "getAllActive", description = "Отправляет все объекты из базы со статусом \"ACTIVE\"",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Объекты найдены."),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права read у клиента.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"read", "ROLE_USER", "ROLE_ADMIN"})
    )
    public ResponseEntity<List<D>> getAllActive(@RequestParam(required = false, defaultValue = "true") Boolean is) {
        return ResponseEntity.ok(this.rawGetAllActive(is));
    }

    /**
     * Получает объект по его id из базы данных.
     *
     * @param id Идентификатор объекта
     * @return Объект с заданным id
     */
    @RequestMapping(value = "/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Operation(method = "getById", description = "Отправляет объект по его id из базы",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Объект найден."),
                    @ApiResponse(responseCode = "400", description = "Не передан id.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права read у клиента.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "404", description = "Не найден объект с заданным id.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            }
    )
    public ResponseEntity<D> getById(@PathVariable Long id) {
        return ResponseEntity.ok(this.rawGetById(id));
    }

}
