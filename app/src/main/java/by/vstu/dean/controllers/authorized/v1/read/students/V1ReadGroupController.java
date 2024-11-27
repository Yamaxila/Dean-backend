package by.vstu.dean.controllers.authorized.v1.read.students;

import by.vstu.dean.core.controllers.BaseReadController;
import by.vstu.dean.core.utils.StringUtils;
import by.vstu.dean.dto.v1.lessons.V1DepartmentDTO;
import by.vstu.dean.dto.v1.students.V1GroupDTO;
import by.vstu.dean.mapper.v1.V1DepartmentMapper;
import by.vstu.dean.mapper.v1.V1GroupMapper;
import by.vstu.dean.models.students.GroupModel;
import by.vstu.dean.repo.GroupModelRepository;
import by.vstu.dean.services.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Контроллер для работы с группами студентов.
 */
@RestController
@RequestMapping("/api/v1/groups")
@Tag(name = "Groups")
@PreAuthorize("hasAnyAuthority('ROLE_SERVICE', 'ROLE_METHODIST')")
public class V1ReadGroupController extends BaseReadController<V1GroupDTO, GroupModel, V1GroupMapper, GroupModelRepository, GroupService> {

    //Маппер кафедр
    private final V1DepartmentMapper departmentMapper;

    /**
     * Конструктор контроллера.
     *
     * @param service Сервис групп студентов
     * @param mapper маппер групп
     * @param departmentMapper маппер кафедр
     */
    public V1ReadGroupController(GroupService service, V1GroupMapper mapper, V1DepartmentMapper departmentMapper) {
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
    @Operation(method = "byYear", description = "Отправляет все группы из базы по году окончания")
    public ResponseEntity<List<V1GroupDTO>> getAllByYearEnd(@RequestParam Integer year, @RequestParam(required = false, defaultValue = "true") Boolean is) {
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
    @Operation(method = "getByName")
    public ResponseEntity<V1GroupDTO> getByName(@RequestParam String name) {
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
    @Operation(method = "getDepartment")
    public ResponseEntity<V1DepartmentDTO> getDepartment(@PathVariable Long id) {

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
    @Operation(method = "getAllDaytime", description = "Отправляет все группы из базы, обучающиеся на дневном отделении")
    public ResponseEntity<List<V1GroupDTO>> getAllDaytime(@RequestParam(required = false, defaultValue = "true") Boolean active) {
        return ResponseEntity.ok(this.mapper.toDto(this.service.getAllActive(active).stream().filter(gr -> gr.getFaculty().isDaytime()).toList()));
    }

    /**
     * Получение всех групп по году, семестру или факультету.
     *
     * @return Список групп по году, семестру или факультет
     */
    @RequestMapping(value = "/by",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Operation(method = "getAllBy", description = "Отправляет все группы из базы по году, семестру или факультету")
    public ResponseEntity<List<V1GroupDTO>> getAllBy(@RequestParam(required = false, defaultValue = "-1") String year, @RequestParam(required = false, defaultValue = "") String semester, @RequestParam(required = false, defaultValue = "-1") String facultyId) {

        List<GroupModel> groups = Collections.synchronizedList(this.service.getAll());

        if (!StringUtils.canBeInt(year))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if (!StringUtils.canBeInt(facultyId))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        long lFacultyId = Long.parseLong(facultyId);
        Integer iYear = Integer.parseInt(year);

        if (iYear != -1) {
            groups = Collections.synchronizedList(groups.stream().filter(p -> p.getYearStart() <= iYear && p.getYearEnd() >= iYear).toList());
        }

        if (!semester.isEmpty()) {
            groups = Collections.synchronizedList(groups.stream().filter(p -> p.getEndSemester().getName().equals(semester)).toList());
        }

        if (lFacultyId != -1) {
            groups = Collections.synchronizedList(groups.stream().filter(p -> p.getFaculty().getId().equals(lFacultyId)).toList());
        }

        return ResponseEntity.ok(this.mapper.toDto(groups.stream().sorted(Comparator.comparing(GroupModel::getStatus).thenComparing(GroupModel::getYearStart)).toList()));
    }

    /**
     * Получение всех годов обучения.
     *
     * @return Список годов обучения
     */
    @RequestMapping(value = "/years",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Operation(method = "getAllBy", description = "Отправляет список годов обучения")
    public ResponseEntity<List<Integer>> getAllYears() {
        List<GroupModel> groups = Collections.synchronizedList(this.service.getAll());
        List<Integer> out = new java.util.ArrayList<>(groups.stream().map(GroupModel::getYearEnd).distinct().toList());
        out.addAll(groups.stream().map(GroupModel::getYearStart).distinct().toList());
        return ResponseEntity.ok(out.stream().distinct().toList());
    }

}
