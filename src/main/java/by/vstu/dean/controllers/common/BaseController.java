package by.vstu.dean.controllers.common;

import by.vstu.dean.anotations.ApiSecurity;
import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.DBBaseModelRepository;
import by.vstu.dean.services.BaseService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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

/**
 * Базовый контроллер для работы с объектами базы данных.
 *
 * @param <O> Тип объекта
 * @param <R> Тип репозитория
 * @param <S> Тип сервиса
 */
@RequiredArgsConstructor
public abstract class BaseController<O extends DBBaseModel, R extends DBBaseModelRepository<O>, S extends BaseService<O, R>> {

    /**
     * Сервис для работы с объектами.
     */
    protected final S service;

    /**
     * Получает все объекты из базы данных.
     *
     * @return Список объектов
     */
    @RequestMapping(value = "/",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read')")
    @ApiOperation(value = "getAll", notes = "Отправляет все объекты из базы")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<O>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
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
    @PreAuthorize("#oauth2.hasScope('read')")
    @ApiOperation(value = "getAllActive", notes = "Отправляет все объекты из базы со статусом \"ACTIVE\"")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<O>> getAllActive() {
        return new ResponseEntity<>(this.service.getAllActive(), HttpStatus.OK);
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
    @PreAuthorize("#oauth2.hasScope('read')")
    @ApiOperation(value = "getById", notes = "Отправляет объект по его id из базы")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<O> getById(@PathVariable Long id) {
        Optional<O> groupModel = (Optional<O>) this.service.getById(id);
        return groupModel.map(model -> new ResponseEntity<>(model, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Сохраняет объект в базу данных и возвращает его с установленным id.
     *
     * @param o Объект для сохранения
     * @return Сохраненный объект с установленным id
     */
    @RequestMapping(value = "/",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('write')")
    @ApiOperation(value = "put", notes = "Сохраняет объект в базу данных и возвращает его же с установленным id")
    @ApiSecurity(scopes = {"write"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<O> put(@RequestParam O o) {
        return new ResponseEntity<>(this.service.save(o), HttpStatus.OK);
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
    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('write')")
    @ApiOperation(value = "deleteById", notes = "Помечает объект по id в базе данных, как удаленный и возвращает его же с установленным статусом DELETED")
    @ApiSecurity(scopes = {"write"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<O> deleteById(@PathVariable Long id) {
        if (id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<O>(this.service.delete(id), HttpStatus.OK);
    }
}