package by.vstu.dean.core.controllers;

import by.vstu.dean.core.dto.BaseDTO;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Базовый контроллер для работы с объектами базы данных.
 *
 * @param <D> DTO
 * @param <O> Тип объекта
 * @param <M> Маппер
 * @param <R> Тип репозитория
 * @param <S> Тип сервиса
 */
@Slf4j
public abstract class BaseController<D extends BaseDTO, O extends DBBaseModel, M extends BaseMapperInterface<D, O>, R extends DBBaseModelRepository<O>, S extends BaseService<O, R>>
        extends PublicController<D, O, M, R, S> {

    public BaseController(S service, M mapper) {
        super(service, mapper);
    }

    /**
     * Сохраняет объект в базу данных и возвращает его с установленным id.
     *
     * @param dto Объект для сохранения
     * @return Сохраненный объект с установленным id
     */
    @RequestMapping(value = "/dto",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    @PreAuthorize("#oauth2.hasScope('write') AND (hasAnyRole('ROLE_ADMIN'))")
    @Operation(method = "put",
            description = "Сохраняет объект в базу данных и возвращает его же с установленным id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешное сохранение DTO."),
                    @ApiResponse(responseCode = "400", description = "Не передан dto.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права write у клиента или роли ADMIN.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"write", "ROLE_ADMIN"})
    )
    public ResponseEntity<D> put(@RequestBody D dto) {
        return new ResponseEntity<>(this.rawPut(dto), HttpStatus.OK);
    }

    /**
     * Сохраняет объект в базу данных и возвращает его с установленным id.
     *
     * @param model Объект для сохранения
     * @return Сохраненный объект с установленным id
     */
    @RequestMapping(value = "/model",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    @PreAuthorize("#oauth2.hasScope('write') AND (hasAnyRole('ROLE_ADMIN'))")
    @Operation(method = "putModel",
            description = "Сохраняет объект в базу данных и возвращает его же с установленным id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешное сохранение объекта."),
                    @ApiResponse(responseCode = "400", description = "Не передана модель.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права write у клиента или роли ADMIN.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"write", "ROLE_ADMIN"})
    )
    public ResponseEntity<O> putModel(@RequestBody O model) {
        return ResponseEntity.ok(this.rawPutModel(model));
    }

    /**
     * Помечает объект в базе данных по его id как удаленный и возвращает его с установленным статусом DELETED.
     *
     * @param id Идентификатор объекта для удаления
     * @return Объект с установленным статусом DELETED
     */
    @RequestMapping(value = "/{id}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    @PreAuthorize("#oauth2.hasScope('write') AND (hasAnyRole('ROLE_ADMIN'))")
    @Operation(method = "deleteById",
            description = "Помечает объект по id в базе данных, как удаленный и возвращает его же с установленным статусом DELETED",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Успешное удаление из базы."),
                    @ApiResponse(responseCode = "400", description = "Не передан id.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права write у клиента или роли ADMIN.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "404", description = "Не найден объект с заданным id.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"write", "ROLE_ADMIN"})
    )
    public ResponseEntity<D> deleteById(@PathVariable Long id) {
        return ResponseEntity.ok(this.rawDeleteById(id));
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
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @Operation(method = "getById", description = "Отправляет объект по его id из базы",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Объект найден."),
                    @ApiResponse(responseCode = "400", description = "Не передан id.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права read у клиента.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "404", description = "Не найден объект с заданным id.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"read", "ROLE_USER", "ROLE_ADMIN"})
    )
    public ResponseEntity<D> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    /**
     * Получает все объекты из базы данных.
     * P.S. Тут только меняются права доступа
     *
     * @return Список активных объектов
     */
    @RequestMapping(value = "",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @Operation(method = "getAll", description = "Отправляет все объекты из базы",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Объекты найдены."),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права read у клиента или ролей USER, ADMIN.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"read", "ROLE_USER", "ROLE_ADMIN"})
    )
    @Override
    public ResponseEntity<List<D>> getAll() {
        return super.getAll();
    }

    /**
     * Получает все активные объекты из базы данных.
     * P.S. Тут только меняются права доступа
     *
     * @return Список активных объектов
     */
    @RequestMapping(value = "/active",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @Operation(method = "getAllActive", description = "Отправляет все объекты из базы со статусом \"ACTIVE\"",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Объекты найдены."),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права read у клиента.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"read", "ROLE_USER", "ROLE_ADMIN"})
    )
    @Override
    public ResponseEntity<List<D>> getAllActive(Boolean is) {
        return super.getAllActive(is);
    }
}