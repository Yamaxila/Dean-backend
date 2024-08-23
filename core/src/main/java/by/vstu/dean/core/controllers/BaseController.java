package by.vstu.dean.core.controllers;

import by.vstu.dean.core.anotations.ApiSecurity;
import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.core.services.BaseService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
    @ApiOperation(value = "getAll", notes = "Отправляет все объекты из базы")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<D>> getAll() {
        return new ResponseEntity<>(this.mapper.toDto(this.service.getAll()), HttpStatus.OK);
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
    @ApiOperation(value = "rsql", notes = "Получает объекты из базы данных через rsql-запрос")
    @ApiSecurity(scopes = {"rsql"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<List<O>> getAllRSql(@RequestParam(required = false, defaultValue = "id>0") String sql) {
        return new ResponseEntity<>(this.service.rsql(sql), HttpStatus.OK);
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
    @ApiOperation(value = "dto_rsql", notes = "Получает DTO из базы данных через rsql-запрос")
    @ApiSecurity(scopes = {"rsql"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<List<D>> getAllRSqlDTO(@RequestParam(required = false, defaultValue = "id>0") String sql) {
        return new ResponseEntity<>(this.mapper.toDto(this.service.rsql(sql)), HttpStatus.OK);
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
    @ApiOperation(value = "getAllActive", notes = "Отправляет все объекты из базы со статусом \"ACTIVE\"")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<D>> getAllActive(@RequestParam(required = false, defaultValue = "true") Boolean is) {
        return new ResponseEntity<>(this.mapper.toDto(this.service.getAllActive(is)), HttpStatus.OK);
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
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @ApiOperation(value = "getById", notes = "Отправляет объект по его id из базы")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<D> getById(@PathVariable Long id) {
        Optional<O> byId = this.service.getById(id);
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
    @ApiOperation(value = "put", notes = "Сохраняет объект в базу данных и возвращает его же с установленным id")
    @ApiSecurity(scopes = {"write"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<D> put(@RequestBody D dto) {
        if(dto == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
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
    @ApiOperation(value = "putModel", notes = "Сохраняет объект в базу данных и возвращает его же с установленным id")
    @ApiSecurity(scopes = {"write"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<O> putModel(@RequestBody O model) {
        if(model == null)
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(this.service.save(model), HttpStatus.OK);
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
    @ApiOperation(value = "deleteById", notes = "Помечает объект по id в базе данных, как удаленный и возвращает его же с установленным статусом DELETED")
    @ApiSecurity(scopes = {"write"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<D> deleteById(@PathVariable Long id) {
        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Optional<O> byId = this.service.getById(id);
        return byId.map(model -> new ResponseEntity<>(this.mapper.toDto(model), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}