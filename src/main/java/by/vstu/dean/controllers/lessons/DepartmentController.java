package by.vstu.dean.controllers.lessons;

import by.vstu.dean.anotations.ApiSecurity;
import by.vstu.dean.controllers.common.BaseController;
import by.vstu.dean.dto.future.lessons.DepartmentDTO;
import by.vstu.dean.dto.future.lessons.TeacherDTO;
import by.vstu.dean.dto.mapper.DepartmentMapper;
import by.vstu.dean.future.models.lessons.DepartmentModel;
import by.vstu.dean.future.repo.DepartmentModelRepository;
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
@RequestMapping("/api/departments/")
@Api(tags = "Departments", description = "Кафедры")
public class DepartmentController extends BaseController<DepartmentDTO, DepartmentModel, DepartmentMapper, DepartmentModelRepository, DepartmentService> {

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис кафедр
     */
    public DepartmentController(DepartmentService service) {
        super(service);
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

        if(o.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(this.service.toDto(o.get()).getTeachers().stream().toList(), HttpStatus.OK);
//        return o.map(departmentModel -> new ResponseEntity<>(departmentModel.getTeachers().stream().map(TeacherDepartmentMerge::getTeacher).toList(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));

    }
}