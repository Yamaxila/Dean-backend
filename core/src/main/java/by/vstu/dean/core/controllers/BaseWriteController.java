package by.vstu.dean.core.controllers;

import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.core.trowable.BadRequestException;
import by.vstu.dean.core.trowable.EntityNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Slf4j
public class BaseWriteController<D extends BaseDTO, O extends DBBaseModel, M extends BaseMapperInterface<D, O>, R extends DBBaseModelRepository<O>, S extends BaseService<O, R>> {

    private final ControllerBaseLogic<D, O, M, R, S> baseLogic;

    /**
     * Сервис для работы с объектами.
     */
    protected final S service;

    /**
     * Маппер.
     */
    protected final M mapper;

    public BaseWriteController(S service, M mapper) {
        this.service = service;
        this.mapper = mapper;
        this.baseLogic = new ControllerBaseLogic<>(service, mapper);
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
        return ResponseEntity.ok(this.baseLogic.rawPut(dto));
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
     * Сохраняет объект в базу данных и возвращает его с установленным id.
     *
     * @param model Объект для сохранения
     * @return Сохраненный объект с установленным id
     */
    @Operation(hidden = true)
    protected O rawPutModel(O model) {
        if (model == null) {
            log.warn("entity is empty!");
            throw new BadRequestException();
        }
        return this.service.save(model);
    }

    /**
     * Помечает объект в базе данных по его id как удаленный и возвращает его с установленным статусом DELETED.
     *
     * @param id Идентификатор объекта для удаления
     * @return Объект с установленным статусом DELETED
     */
    @Operation(hidden = true)
    protected D rawDeleteById(Long id) {
        if (id == null) {
            log.warn("id is null!");
            throw new BadRequestException();
        }
        Optional<O> byId = this.service.getById(id);

        if (byId.isEmpty())
            log.warn("Cannot to find entity with id {}", id);

        return byId.map(this.mapper::toDto).orElseThrow(EntityNotFoundException::new);
    }


}
