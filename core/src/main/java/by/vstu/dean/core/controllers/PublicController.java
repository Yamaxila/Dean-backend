package by.vstu.dean.core.controllers;

import by.vstu.dean.core.dto.PublicDTO;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.core.utils.ValidationUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public class PublicController<D extends PublicDTO, O extends DBBaseModel, M extends BaseMapperInterface<D, O>, R extends DBBaseModelRepository<O>, S extends BaseService<O, R>> {

    /**
     * Сервис для работы с объектами.
     */
    protected final S service;

    /**
     * Маппер.
     */
    protected final M mapper;


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
        List<O> tempO = this.service.getAll();

        if (tempO == null) {
            log.error("Can't get data from database!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<D> tempD = this.mapper.toDto(tempO);

        if (tempD == null) {
            log.error("List mapping error!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(tempD, HttpStatus.OK);
    }


    /**
     * Получает все активные объекты из базы данных.
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
    public ResponseEntity<List<D>> getAllActive(@RequestParam(required = false, defaultValue = "true") Boolean is) {

        List<O> tempO = this.service.getAllActive(is);

        if (!ValidationUtils.isObjectValid(tempO)) {
            log.error("Can't get active={} data from database!", is);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<D> tempD = this.mapper.toDto(tempO);

        if (!ValidationUtils.isObjectValid(tempO)) {
            log.error("List mapping error! active={}", is);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(tempD, HttpStatus.OK);
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
        Optional<O> byId = this.service.getById(id);
        return byId.map(model -> new ResponseEntity<>(this.mapper.toDto(model), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}
