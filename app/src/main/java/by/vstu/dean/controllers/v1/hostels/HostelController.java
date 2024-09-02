package by.vstu.dean.controllers.v1.hostels;

import by.vstu.dean.core.controllers.BaseController;
import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.dto.v1.hostels.HostelRoomDTO;
import by.vstu.dean.dto.v1.students.StudentDTO;
import by.vstu.dean.mapper.v1.HostelRoomMapper;
import by.vstu.dean.mapper.v1.StudentMapper;
import by.vstu.dean.models.hostels.HostelRoomModel;
import by.vstu.dean.repo.HostelRoomModelRepository;
import by.vstu.dean.services.HostelRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hostels/")
@Tag(name = "HostelController", description = "Общежития и комнаты")
public class HostelController extends BaseController<HostelRoomDTO, HostelRoomModel, HostelRoomMapper, HostelRoomModelRepository, HostelRoomService> {
    @Autowired
    private StudentMapper studentMapper;

    public HostelController(HostelRoomService service, HostelRoomMapper mapper) {
        super(service, mapper);
    }

    /**
     * Получает все комнаты указанного общежития.
     *
     * @param id Идентификатор общежития.
     * @return Список комнат указанного общежития.
     */
    @RequestMapping(value = "{id}/rooms", produces = {"application/json"}, method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @Operation(method = "getAllRooms", description = "Отправляет все комнаты указанного общежития")
    public ResponseEntity<List<HostelRoomDTO>> findAllByHostelId(@PathVariable int id) {
        return new ResponseEntity<>(this.mapper.toDto(this.service.getAll()).stream().filter(p -> p.getHostel().ordinal() == id).toList(), HttpStatus.OK);
    }

    /**
     * Получает все активные комнаты указанного общежития.
     *
     * @param id Идентификатор общежития.
     * @param is Признак активности комнаты (по умолчанию true).
     * @return Список активных комнат указанного общежития.
     */
    @RequestMapping(value = "{id}/rooms/active", produces = {"application/json"}, method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @Operation(method = "getAllRoomsActive", description = "Отправляет все активные комнаты указанного общежития")
    public ResponseEntity<List<HostelRoomDTO>> findAllByHostelIdActive(@PathVariable int id, @RequestParam(required = false, defaultValue = "true") Boolean is) {
        return new ResponseEntity<>(this.mapper.toDto(this.service.getAllActive(is)).stream().filter(p -> p.getHostel().ordinal() == id).toList(), HttpStatus.OK);
    }

    /**
     * Получает все активные комнаты указанного общежития по этажу.
     *
     * @param id    Идентификатор общежития.
     * @param floor Номер этажа.
     * @return Список активных комнат указанного общежития по этажу.
     */
    @RequestMapping(value = "{id}/floors/{floor}", produces = {"application/json"}, method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @Operation(method = "getAllByHostelAndFloor", description = "Отправляет все активные комнаты указанного общежития по этажу")
    public ResponseEntity<List<HostelRoomDTO>> findAllByHostelIdAndFloor(@PathVariable int id, @PathVariable int floor) {
        return new ResponseEntity<>(this.mapper.toDto(this.service.getAll()).stream().filter(p -> p.getHostel().ordinal() == id && p.getFloor() == floor).toList(), HttpStatus.OK);
    }

    /**
     * Получает комнату по её идентификатору.
     *
     * @param roomId Идентификатор комнаты.
     * @return Комната с указанным идентификатором.
     */
    @Override
    @RequestMapping(value = "/rooms/{roomId}", produces = {"application/json"}, method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @Operation(method = "getAllRooms", description = "Отправляет комнату по её id")
    public ResponseEntity<HostelRoomDTO> getById(@PathVariable Long roomId) {
        return super.getById(roomId);
    }

    /**
     * Получает всех студентов, проживающих в комнате.
     *
     * @param id Идентификатор комнаты.
     * @return Список студентов, проживающих в комнате.
     */
    @RequestMapping(value = "/rooms/{id}/students", produces = {"application/json"}, method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @Operation(method = "getAllRooms", description = "Отправляет всех студентов, проживавших в комнате")
    public ResponseEntity<List<StudentDTO>> getAllRoomStudents(@PathVariable Long id) {
        Optional<HostelRoomModel> o = this.service.getById(id);
        return o.map(hostelRoomModel -> new ResponseEntity<>(this.studentMapper.toDto(hostelRoomModel.getStudents().stream().toList()), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Получает всех активных (или нет) студентов, проживающих в комнате.
     *
     * @param roomId Идентификатор комнаты.
     * @param is     Признак активности студентов (по умолчанию true).
     * @return Список активных (или нет) студых студентов, проживающих в комнате.
     */

    @RequestMapping(value = "/rooms/{roomId}/students/active", produces = {"application/json"}, method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @Operation(method = "getAllRooms", description = "Отправляет всех активных(или нет) студентов, проживавших в комнате")
    public ResponseEntity<List<StudentDTO>> getAllRoomStudentsActive(@PathVariable Long roomId, @RequestParam(required = false, defaultValue = "true") Boolean is) {
        Optional<HostelRoomModel> o = this.service.getById(roomId);

        return o.map(hostelRoomModel -> new ResponseEntity<>(this.studentMapper.toDto(hostelRoomModel.getStudents().stream().filter(p -> p.getStatus().equals(is ? EStatus.ACTIVE : EStatus.DELETED)).toList()), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Получает всех подтвержденных (или нет) студентов, проживающих в комнате.
     *
     * @param roomId Идентификатор комнаты.
     * @param is     Признак подтверждения студентов (по умолчанию true).
     * @return Список подтвержденных (или нет) студентов, проживающих в комнате.
     */
    @RequestMapping(value = "/rooms/{roomId}/students/approved", produces = {"application/json"}, method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_USER', 'ROLE_ADMIN'))")
    @Operation(method = "getAllRooms", description = "Отправляет всех подтвержденных(или нет) студентов, проживавших в комнате")
    public ResponseEntity<List<StudentDTO>> getAllRoomStudentsApproved(@PathVariable Long roomId, @RequestParam(required = false, defaultValue = "true") Boolean is) {
        Optional<HostelRoomModel> o = this.service.getById(roomId);
        return o.map(hostelRoomModel -> new ResponseEntity<>(this.studentMapper.toDto(hostelRoomModel.getStudents().stream().filter(p -> p.isApproved() == is).toList()), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Назначает студента в комнату.
     *
     * @param roomId Идентификатор комнаты.
     * @param studentId Идентификатор студента, которого назначают в комнату.
     * @return Комната, в которую назначают студента.
     */
    @RequestMapping(value = "/rooms/{roomId}/students", produces = {"application/json"}, method = RequestMethod.POST)
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_ADMIN'))")
    @Operation(method = "postStudent", description = "Прикрепляет студента к комнате")
    public ResponseEntity<HostelRoomDTO> postStudent(@PathVariable Long roomId, @RequestParam Long studentId) {
        Optional<HostelRoomModel> o = this.service.getById(roomId);
        return o.map(hostelRoomModel -> new ResponseEntity<>(this.mapper.toDto(this.service.setStudent(hostelRoomModel, studentId)), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * Открепляет студента от комнаты.
     *
     * @param roomId Идентификатор комнаты.
     * @param studentId Идентификатор студента, которого открепляют от комнаты.
     * @return Комната, от которой открепляют студента.
     */
    @RequestMapping(value = "/rooms/{roomId}/students", produces = {"application/json"}, method = RequestMethod.DELETE)
    @PreAuthorize("#oauth2.hasScope('read') AND (hasAnyRole('ROLE_ADMIN'))")
    @Operation(method = "removeStudent", description = "Открепляет студента от комнаты")
    public ResponseEntity<HostelRoomDTO> deleteStudent(@PathVariable Long roomId, @RequestParam Long studentId) {
        Optional<HostelRoomModel> o = this.service.getById(roomId);
        return o.map(hostelRoomModel -> new ResponseEntity<>(this.mapper.toDto(this.service.removeStudent(hostelRoomModel, studentId)), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}