package by.vstu.dean.controllers.authorized.v1.read.lessons;

import by.vstu.dean.core.anotations.ApiSecurity;
import by.vstu.dean.core.controllers.BaseController;
import by.vstu.dean.dto.v1.lessons.V1TeacherDTO;
import by.vstu.dean.mapper.v1.V1TeacherMapper;
import by.vstu.dean.models.lessons.TeacherModel;
import by.vstu.dean.models.merge.TeacherDepartmentMerge;
import by.vstu.dean.repo.TeacherDepartmentMergeRepository;
import by.vstu.dean.repo.TeacherModelRepository;
import by.vstu.dean.services.TeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер для работы с преподавателями.
 */
@RestController
@RequestMapping("/api/v1/teachers/")
@Tag(name = "Teachers", description = "Преподаватели")
public class V1TeacherController extends BaseController<V1TeacherDTO, TeacherModel, V1TeacherMapper, TeacherModelRepository, TeacherService> {

    @Autowired
    private TeacherDepartmentMergeRepository teacherDepartmentMergeRepository;

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис преподавателей
     * @param mapper Маппер
     */
    public V1TeacherController(TeacherService service, V1TeacherMapper mapper) {
        super(service, mapper);
    }


    @RequestMapping(value = "/merges/",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('rsql') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @Operation(method = "getAllTeacherDepartmentMerge", description = "Отправляет все объекты из базы")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<TeacherDepartmentMerge>> getAllTeacherDepartmentMerge() {
        return new ResponseEntity<>(this.teacherDepartmentMergeRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "/merges/put",
            produces = {"application/json"},
            method = RequestMethod.PUT)
    @PreAuthorize("#oauth2.hasScope('rsql') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @Operation(method = "merges", description = "Отправляет все объекты из базы")
    @ApiSecurity(scopes = {"rsql"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<TeacherDepartmentMerge>> putMerges(@RequestBody List<TeacherDepartmentMerge> teacherDepartmentMerges) {
        return new ResponseEntity<>(this.teacherDepartmentMergeRepository.saveAllAndFlush(teacherDepartmentMerges), HttpStatus.OK);
    }
}