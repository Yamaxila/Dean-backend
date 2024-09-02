package by.vstu.dean.core.controllers;

import by.vstu.dean.core.anotations.ApiSecurity;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.core.utils.ValidationUtils;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Базовый контроллер для работы с объектами базы данных.
 *
 * @param <D> DTO
 * @param <O> Тип объекта
 * @param <M> Маппер
 * @param <R> Тип репозитория
 * @param <S> Тип сервиса
 */
@RequiredArgsConstructor
@Slf4j
public abstract class BaseController<D extends BaseDTO, O extends DBBaseModel, M extends BaseMapperInterface<D, O>, R extends DBBaseModelRepository<O>, S extends BaseService<O, R>> {

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
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @Operation(method = "getAll", description = "Отправляет все объекты из базы")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<D>> getAll() {
        List<O> tempO = this.service.getAll();

        if(tempO == null) {
            log.error("Can't get data from database!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<D> tempD = this.mapper.toDto(tempO);

        if(tempD == null) {
            log.error("List mapping error!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(tempD, HttpStatus.OK);
    }

    /**
     * Получает объекты из базы данных через rsql-запрос.
     *
     * @return Список объектов
     */
    @RequestMapping(value = "/rsql",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('rsql') AND (hasAnyRole('ROLE_ADMIN'))")
    @Operation(method = "rsql", description = "Получает объекты из базы данных через rsql-запрос")
    @ApiSecurity(scopes = {"rsql"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<List<O>> getAllRSql(@RequestParam(required = false, defaultValue = "id>0") String sql) {
        try {
            return new ResponseEntity<>(this.service.rsql(sql), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Can't execute rsql!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Получает объекты из базы данных через rsql-запрос.
     *
     * @return Список объектов
     */
    @RequestMapping(value = "/dto_rsql",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('rsql') AND (hasAnyRole('ROLE_ADMIN'))")
    @Operation(method = "dto_rsql", description = "Получает DTO из базы данных через rsql-запрос")
    @ApiSecurity(scopes = {"rsql"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<List<D>> getAllRSqlDTO(@RequestParam(required = false, defaultValue = "id>0") String sql) {
        try {

            List<O> tempO = this.service.rsql(sql);

            if(!ValidationUtils.isObjectValid(tempO)) {
                log.error("Can't execute rsql before dto mapping!");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            List<D> tempD = this.mapper.toDto(tempO);

            if(!ValidationUtils.isObjectValid(tempO)) {
                log.error("List from rsql request mapping error!");
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(tempD, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Can't execute rsql!");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
    @Operation(method = "getAllActive", description = "Отправляет все объекты из базы со статусом \"ACTIVE\"")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<D>> getAllActive(@RequestParam(required = false, defaultValue = "true") Boolean is) {

        List<O> tempO = this.service.getAllActive(is);

        if(!ValidationUtils.isObjectValid(tempO)) {
            log.error("Can't get active={} data from database!", is);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<D> tempD = this.mapper.toDto(tempO);

        if(!ValidationUtils.isObjectValid(tempO)) {
            log.error("List mapping error! active={}", is);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(tempD, HttpStatus.OK);
    }

    /**
     * Получает объект по его id из базы данных.
     *
     * @param entity Идентификатор объекта
     * @return Объект с заданным id
     */
    @RequestMapping(value = "/{<entity>id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @Operation(method = "getById", description = "Отправляет объект по его id из базы")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<D> getById(@PathVariable Long entity) {
        Optional<O> byId = this.service.getById(entity);
        return byId.map(model -> new ResponseEntity<>(this.mapper.toDto(model), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Сохраняет объект в базу данных и возвращает его с установленным id.
     *
     * @param dto Объект для сохранения
     * @return Сохраненный объект с установленным id
     */
    @RequestMapping(value = "/",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    @PreAuthorize("#oauth2.hasScope('write') AND (hasAnyRole('ROLE_ADMIN'))")
    @Operation(method = "put", description = "Сохраняет объект в базу данных и возвращает его же с установленным id")
    @ApiSecurity(scopes = {"write"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<D> put(@RequestBody D dto) {
        if(dto == null) {
            log.warn("DTO is empty!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(this.mapper.toDto(this.service.save(this.mapper.toEntity(dto))), HttpStatus.OK);
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
    @Operation(method = "putModel", description = "Сохраняет объект в базу данных и возвращает его же с установленным id")
    @ApiSecurity(scopes = {"write"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<O> putModel(@RequestBody O model) {
        if(model == null) {
            log.warn("entity is empty!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(this.service.save(model), HttpStatus.OK);
    }

    /**
     * Помечает объект в базе данных по его id как удаленный и возвращает его с установленным статусом DELETED.
     *
     * @param id Идентификатор объекта для удаления
     * @return Объект с установленным статусом DELETED
     */
    @RequestMapping(value = "/{<entity>id}",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    @PreAuthorize("#oauth2.hasScope('write') AND (hasAnyRole('ROLE_ADMIN'))")
    @Operation(method = "deleteById", description = "Помечает объект по id в базе данных, как удаленный и возвращает его же с установленным статусом DELETED")
    @ApiSecurity(scopes = {"write"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<D> deleteById(@PathVariable Long id) {
        if (id == null) {
            log.warn("id is null!");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<O> byId = this.service.getById(id);

        if (byId.isEmpty())
            log.warn("Cannot to find entity with id {}", id);

        return byId.map(model -> new ResponseEntity<>(this.mapper.toDto(model), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}