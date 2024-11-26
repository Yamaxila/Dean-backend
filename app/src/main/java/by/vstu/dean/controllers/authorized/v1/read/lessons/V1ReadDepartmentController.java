package by.vstu.dean.controllers.authorized.v1.read.lessons;

import by.vstu.dean.core.controllers.BaseReadController;
import by.vstu.dean.dto.v1.lessons.V1DepartmentDTO;
import by.vstu.dean.dto.v1.lessons.V1TeacherDTO;
import by.vstu.dean.mapper.v1.V1DepartmentMapper;
import by.vstu.dean.mapper.v1.V1TeacherMapper;
import by.vstu.dean.models.lessons.DepartmentModel;
import by.vstu.dean.models.merge.TeacherDepartmentMerge;
import by.vstu.dean.repo.DepartmentModelRepository;
import by.vstu.dean.repo.TeacherDepartmentMergeRepository;
import by.vstu.dean.services.DepartmentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@RequestMapping("/api/v1/departments")
@Tag(name = "Departments", description = "Кафедры")
@PreAuthorize("hasAnyAuthority('ROLE_SERVICE', 'ROLE_METHODIST')")
public class V1ReadDepartmentController extends BaseReadController<V1DepartmentDTO, DepartmentModel, V1DepartmentMapper, DepartmentModelRepository, DepartmentService> {

    private final V1TeacherMapper teacherMapper;
    private final TeacherDepartmentMergeRepository mergeRepo;

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис кафедр
     * @param mapper Маппер кафедр.
     * @param mergeRepo Репозиторий для работы с промежуточным объектом {@link TeacherDepartmentMerge}
     * @param teacherMapper Маппер преподавателей
     */
    public V1ReadDepartmentController(DepartmentService service, V1DepartmentMapper mapper, V1TeacherMapper teacherMapper, TeacherDepartmentMergeRepository mergeRepo) {
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
    @Operation(method = "getTeachers", description = "Получает список преподавателей кафедры по её id")
    public ResponseEntity<List<V1TeacherDTO>> getTeachers(@PathVariable Long id) {
        Optional<DepartmentModel> o = this.service.getById(id);
        return o.map(departmentModel -> new ResponseEntity<>(this.teacherMapper.toDto(departmentModel.getTeachers().stream().toList()), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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
    @Operation(method = "getDepartmentByTeacher", description = "Получает кафедру по id преподавателя")
    public ResponseEntity<V1DepartmentDTO> getDepartmentByTeacher(@RequestParam Long teacherId) {
        Optional<TeacherDepartmentMerge> o = this.mergeRepo.findByTeacherId(teacherId);
        return o.map(tdm -> new ResponseEntity<>(this.mapper.toDto(tdm.getDepartment()), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}