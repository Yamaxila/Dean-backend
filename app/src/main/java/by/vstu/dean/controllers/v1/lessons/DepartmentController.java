package by.vstu.dean.controllers.v1.lessons;

import by.vstu.dean.core.anotations.ApiSecurity;
import by.vstu.dean.controllers.v1.BaseController;
import by.vstu.dean.dto.v1.lessons.DepartmentDTO;
import by.vstu.dean.dto.v1.lessons.TeacherDTO;
import by.vstu.dean.dto.mapper.DepartmentMapper;
import by.vstu.dean.dto.mapper.TeacherMapper;
import by.vstu.dean.models.lessons.DepartmentModel;
import by.vstu.dean.repo.DepartmentModelRepository;
import by.vstu.dean.services.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Контроллер для работы с кафедрами.
 */
@RestController
@RequestMapping("/api/v1/departments/")
@Api(tags = "Departments", description = "Кафедры")
public class DepartmentController extends BaseController<DepartmentDTO, DepartmentModel, DepartmentMapper, DepartmentModelRepository, DepartmentService> {

    private final TeacherMapper teacherMapper;

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис кафедр
     */
    public DepartmentController(DepartmentService service, TeacherMapper teacherMapper) {
        super(service);
        this.teacherMapper = teacherMapper;
    }

    /**
     * Получает список преподавателей кафедры по её id.
     *
     * @param id Идентификатор кафедры
     * @return Список преподавателей кафедры
     */
    @RequestMapping(value = "/{id}/teachers",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @ApiOperation(value = "getTeachers", notes = "Получает список преподавателей кафедры по её id")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<TeacherDTO>> getTeachers(@PathVariable Long id) {
        Optional<DepartmentModel> o = this.service.getById(id);
        return o.map(departmentModel -> new ResponseEntity<>(this.teacherMapper.toDto(departmentModel.getTeachers().stream().toList()).stream().toList(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}