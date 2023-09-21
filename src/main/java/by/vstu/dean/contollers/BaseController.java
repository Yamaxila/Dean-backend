package by.vstu.dean.contollers;

import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.services.common.BaseService;
import by.vstu.dean.services.common.IBaseService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

public abstract class BaseController<T extends DBBaseModel> {


    protected HttpHeaders headers;

    @PostConstruct
    private void init(){
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    }

    public abstract IBaseService<T> getService();

    @Secured({"ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<List<T>> getAll(){
        List<T> entities = getService().findAll();
        if(entities.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN"})
    @GetMapping(params = "id", value = "/id")
    public ResponseEntity<T> getById(@RequestParam("id") Long id){
        T entity = getService().findOne(id);
        if(entity == null) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping
    public ResponseEntity<String> put(@RequestBody @Valid T entity){
        getService().update(entity.getId(), entity);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Secured({"ROLE_ADMIN"})
    @PostMapping
    public ResponseEntity<String> post(@RequestBody @Valid T entity){
        getService().save(entity);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Secured({"ROLE_ADMIN"})
    @DeleteMapping(params = "id")
    public ResponseEntity<String> delete(@RequestParam("id") Long id){
        getService().deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
