package by.vstu.dean.controllers;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseController<O extends DBBaseModel, R extends DBBaseModelRepository<O>, S extends BaseService<O, R>> {

    protected final S service;

    @RequestMapping(value="/",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read')")
    @ApiOperation(value = "getAll", notes = "Отправляет все объекты из базы")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<O>> getAll() {
        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
    }

    @RequestMapping(value="/id",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read')")
    @ApiOperation(value = "getById", notes = "Отправляет объект по его id из базы")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<O> getById(@RequestParam Long id) {
        Optional<O> groupModel = (Optional<O>) this.service.getById(id);
        return groupModel.map(model -> new ResponseEntity<>(model, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value="/",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('write')")
    @ApiOperation(value = "put", notes = "Сохраняет объект в базу данных и возвращает его же с установленным id")
    @ApiSecurity(scopes = {"write"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<O> put(@RequestParam O o) {
        return new ResponseEntity<>(this.service.save(o), HttpStatus.OK);
    }

    @RequestMapping(value="/",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('write')")
    @ApiOperation(value = "delete", notes = "Помечает объект в базе данных, как удаленный и возвращает его же с установленным статусом DELETED")
    @ApiSecurity(scopes = {"write"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<O> delete(@RequestParam O o) {
        if(o.getId() == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);


        return new ResponseEntity<>(this.service.delete(o), HttpStatus.OK);
    }
    @RequestMapping(value="/byId",
            produces = {"application/json"},
            method = RequestMethod.DELETE)
    @Secured({"ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('write')")
    @ApiOperation(value = "deleteById", notes = "Помечает объект по id в базе данных, как удаленный и возвращает его же с установленным статусом DELETED")
    @ApiSecurity(scopes = {"write"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<O> deleteById(@RequestParam Long id) {
        if(id == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(this.service.delete(id), HttpStatus.OK);

    }
}
