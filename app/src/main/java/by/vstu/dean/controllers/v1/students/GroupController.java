package by.vstu.dean.controllers.v1.students;

import by.vstu.dean.core.controllers.BaseController;
import by.vstu.dean.core.anotations.ApiSecurity;
import by.vstu.dean.dto.v1.lessons.DepartmentDTO;
import by.vstu.dean.dto.v1.students.GroupDTO;
import by.vstu.dean.mapper.v1.DepartmentMapper;
import by.vstu.dean.mapper.v1.GroupMapper;
import by.vstu.dean.models.students.GroupModel;
import by.vstu.dean.repo.GroupModelRepository;
import by.vstu.dean.services.GroupService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер для работы с группами студентов.
 */
@RestController
@RequestMapping("/api/v1/groups/")
@Api(tags = "Groups")
public class GroupController extends BaseController<GroupDTO, GroupModel, GroupMapper, GroupModelRepository, GroupService> {

    private final DepartmentMapper departmentMapper;

    /**
     * Конструктор контроллера.
     *
     * @param service          Сервис групп студентов
     * @param mapper маппер групп
     * @param departmentMapper маппер кафедр
     */
    public GroupController(GroupService service, GroupMapper mapper, DepartmentMapper departmentMapper) {
        super(service, mapper);
        this.departmentMapper = departmentMapper;
    }

    /**
     * Получение всех групп по году окончания.
     *
     * @param year Год окончания
     * @return Список групп
     */
    @RequestMapping(value = "/byYear",
            produces = {"application/json"},
            method = RequestMethod.GET,
            params = {"year"})
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @ApiOperation(value = "byYear", notes = "Отправляет все группы из базы по году окончания")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<GroupDTO>> getAllByYearEnd(@RequestParam Integer year, @RequestParam(required = false, defaultValue = "true") Boolean is) {
        return new ResponseEntity<>(this.mapper.toDto(this.service.getAllActive(is)).stream().filter(p -> p.getYearEnd().equals(year)).toList(), HttpStatus.OK);
    }

    /**
     * Получение группы по имени.
     *
     * @param name Имя группы
     * @return Группа
     */
    @RequestMapping(value = "/byName",
            produces = {"application/json"},
            method = RequestMethod.GET,
            params = {"name"})
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @ApiOperation(value = "getByName")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<GroupDTO> getByName(@RequestParam String name) {
        return new ResponseEntity<>(this.mapper.toDto(this.service.findByName(name)), HttpStatus.OK);
    }

    /**
     * Получение отделения по идентификатору группы.
     *
     * @param id Идентификатор группы
     * @return Отделение
     */
    @RequestMapping(value = "/{id}/department",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @ApiOperation(value = "getDepartment")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<DepartmentDTO> getDepartment(@PathVariable Long id) {

        Optional<GroupModel> o = this.service.getById(id);

        if (o.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        GroupModel group = o.get();

        if (group.getSpec().getDepartment() == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        return new ResponseEntity<>(this.departmentMapper.toDto(group.getSpec().getDepartment()), HttpStatus.OK);
    }

    /**
     * Получение всех групп дневного отделения.
     *
     * @return Список групп дневного отделения
     */
    @RequestMapping(value = "/daytime",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @ApiOperation(value = "getAllDaytime", notes = "Отправляет все группы из базы, обучающиеся на дневном отделении")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<GroupDTO>> getAllDaytime(@RequestParam(required = false, defaultValue = "true") Boolean active) {
        return new ResponseEntity<>(this.mapper.toDto(
                this.service.getAllActive(active).stream().filter( //FIXME: почему-то объект FacultyModel не имеет метода isDaytimeFaculty
                        gr -> gr.getFaculty().getFacultyType() == 0 || gr.getFaculty().getId() != 7).toList()
        ), HttpStatus.OK);
    }


}
