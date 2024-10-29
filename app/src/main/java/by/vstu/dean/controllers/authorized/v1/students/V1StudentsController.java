package by.vstu.dean.controllers.authorized.v1.students;

import by.vstu.dean.core.anotations.ApiSecurity;
import by.vstu.dean.core.controllers.BaseController;
import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.dto.v1.students.V1StudentDTO;
import by.vstu.dean.mapper.v1.V1StudentMapper;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.repo.StudentModelRepository;
import by.vstu.dean.services.students.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер для работы с объектами студентов.
 */
@RestController
@RequestMapping("/api/v1/students")
@Tag(name = "Students", description = "Студенты")
public class V1StudentsController extends BaseController<V1StudentDTO, StudentModel, V1StudentMapper, StudentModelRepository, StudentService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис студентов
     */
    public V1StudentsController(StudentService service, V1StudentMapper mapper) {
        super(service, mapper);
    }

    /**
     * Получить всех студентов.
     *
     * @return Список студентов
     */
    @RequestMapping(value = "/",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @PreAuthorize("(#oauth2.hasScope('dean_read') AND (hasAnyRole('ROLE_ADMIN') OR hasAnyRole('ROLE_SERVICE')))")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<List<V1StudentDTO>> getAll() {
        return new ResponseEntity<>(this.mapper.toDto(this.service.getAll()), HttpStatus.OK);
    }

    /**
     * Получить всех студентов по идентификатору группы.
     *
     * @param id Идентификатор группы
     * @return Список студентов
     */
    @RequestMapping(value = "/byGroup/{id}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @PreAuthorize("(#oauth2.hasScope('dean_read') AND (hasAnyRole('ROLE_ADMIN') OR hasAnyRole('ROLE_SERVICE')))")
    @Operation(method = "getAllByGroup", description = "Отправляет все объекты из базы по id группы")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<List<StudentModel>> getAllByGroup(@PathVariable Long id) {
        return new ResponseEntity<>(this.service.findAllByGroupId(id), HttpStatus.OK);
    }

    /**
     * Получить всех активных студентов по идентификатору группы.
     *
     * @param id Идентификатор группы
     * @return Список студентов
     */
    @RequestMapping(value = "/byGroup/{id}/active",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @PreAuthorize("(#oauth2.hasScope('dean_read') AND (hasAnyRole('ROLE_ADMIN') OR hasAnyRole('ROLE_SERVICE')))")
    @Operation(method = "getAllByGroup", description = "Отправляет все объекты из базы по id группы")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<List<StudentModel>> getAllActiveByGroup(@PathVariable Long id) {
        return new ResponseEntity<>(this.service.findAllByGroupId(id).stream().filter(p -> p.getStatus().equals(EStatus.ACTIVE)).toList(), HttpStatus.OK);
    }

    /**
     * Получить студента по идентификатору.
     *
     * @param id Идентификатор студента
     * @return Объект студента
     */
    @RequestMapping(value = "/{id}/",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @PreAuthorize("(#oauth2.hasScope('dean_read') AND (hasAnyRole('ROLE_ADMIN') OR hasAnyRole('ROLE_SERVICE')))")
    @Operation(method = "getById", description = "Отправляет объект из базы по id")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<V1StudentDTO> getById(@PathVariable Long id) {
        return super.getById(id);
    }

    /**
     * Получить всех студентов, нуждающихся в общежитии.
     *
     * @return Список студентов
     */
    @RequestMapping(value = "/homeless",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_ADMIN'))")
    @Operation(method = "getAllHomeless", description = "Отправляет все объекты из базы, которые нуждаются в общежитии")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<List<V1StudentDTO>> getAllHomeless() {
        return new ResponseEntity<>(this.mapper.toDto(this.service.getAll().stream().filter(s -> s.isNeedHostel() && s.getStatus().equals(EStatus.ACTIVE)).toList()), HttpStatus.OK);
    }

    /**
     * Установить поле approved студенту по идентификатору.
     *
     * @param id Идентификатор студента
     * @param approved Значение approved(true/false)
     * @return Объект студента
     */
    @RequestMapping(value = "/{id}/approve",
            produces = {"application/json"},
            method = RequestMethod.POST)
    @PreAuthorize("#oauth2.hasScope('write') AND (hasAnyRole('ROLE_ADMIN'))")
    @Operation(method = "setApproved", description = "Устанавливает поле approved для объекта по id")
    @ApiSecurity(scopes = {"write"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<V1StudentDTO> setApproved(@PathVariable Long id, @RequestParam boolean approved) {
        Optional<StudentModel> s = this.service.getById(id);
        if (s.isEmpty()) return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        s.get().setApproved(approved);
        return new ResponseEntity<>(this.mapper.toDto(this.service.save(s.get())), HttpStatus.OK);
    }


}
