package by.vstu.dean.controllers.v1.lessons;

import by.vstu.dean.controllers.v1.BaseController;
import by.vstu.dean.core.anotations.ApiSecurity;
import by.vstu.dean.dto.mapper.DepartmentMapper;
import by.vstu.dean.dto.mapper.TeacherMapper;
import by.vstu.dean.dto.v1.lessons.DepartmentDTO;
import by.vstu.dean.dto.v1.lessons.TeacherDTO;
import by.vstu.dean.models.lessons.DepartmentModel;
import by.vstu.dean.models.merge.TeacherDepartmentMerge;
import by.vstu.dean.repo.DepartmentModelRepository;
import by.vstu.dean.repo.TeacherDepartmentMergeRepository;
import by.vstu.dean.services.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
    private final TeacherDepartmentMergeRepository mergeRepo;

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис кафедр
     */
    public DepartmentController(DepartmentService service, DepartmentMapper mapper, TeacherMapper teacherMapper, TeacherDepartmentMergeRepository mergeRepo) {
        super(service, mapper);
        this.teacherMapper = teacherMapper;
        this.mergeRepo = mergeRepo;
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

    /**
     * Получает кафедру по id преподавателя.
     *
     * @param teacherId Идентификатор преподавателя
     * @return Объект кафедры
     */
    @RequestMapping(
            value = "/byTeacher",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @ApiOperation(value = "getDepartmen tByTeacher", notes = "Получает кафедру по id преподавателя")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<DepartmentDTO> getDepartmentByTeacher(@RequestParam Long teacherId) {
        Optional<TeacherDepartmentMerge> o = this.mergeRepo.findByTeacherId(teacherId);
        return o.map(tdm -> new ResponseEntity<>(this.mapper.toDto(tdm.getDepartment()), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

}