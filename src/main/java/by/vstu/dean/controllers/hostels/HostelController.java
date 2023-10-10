package by.vstu.dean.controllers.hostels;

import by.vstu.dean.anotations.ApiSecurity;
import by.vstu.dean.controllers.common.BaseController;
import by.vstu.dean.future.models.hostels.HostelRoomModel;
import by.vstu.dean.future.models.students.StudentModel;
import by.vstu.dean.future.repo.HostelRoomModelRepository;
import by.vstu.dean.services.HostelRoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/hostels/")
@Api(tags = "HostelController", description = "Общежития и комнаты")
public class HostelController extends BaseController<HostelRoomModel, HostelRoomModelRepository, HostelRoomService> {
    public HostelController(HostelRoomService service) {
        super(service);
    }

    @RequestMapping(value = "{id}/rooms",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read')")
    @ApiOperation(value = "getAllRooms", notes = "Отправляет все комнаты указанного общежития")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<HostelRoomModel>> findAllByHostelId(@PathVariable int id) {
        return new ResponseEntity<>(this.service.getAll().stream().filter(p -> p.getHostel().ordinal() == id).toList(), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/rooms/active",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read')")
    @ApiOperation(value = "getAllRoomsActive", notes = "Отправляет все активные комнаты указанного общежития")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<HostelRoomModel>> findAllByHostelIdActive(@PathVariable int id, @RequestParam(required = false, defaultValue = "true") Boolean is) {
        return new ResponseEntity<>(this.service.getAllActive(is).stream().filter(p -> p.getHostel().ordinal() == id).toList(), HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/rooms/{roomId}",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read')")
    @ApiOperation(value = "getAllRooms", notes = "Отправляет комнату по её id")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<HostelRoomModel> getById(@PathVariable Long roomId) {
        return super.getById(roomId);
    }

    @RequestMapping(value = "/rooms/{id}/students",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read')")
    @ApiOperation(value = "getAllRooms", notes = "Отправляет всех студентов, проживавщих(-ющих) в комнате")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<StudentModel>> getAllRoomStudents(@PathVariable Long id) {
        Optional<HostelRoomModel> o = this.service.getById(id);
        return o.map(hostelRoomModel -> new ResponseEntity<>(hostelRoomModel.getStudents().stream().toList(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/rooms/{roomId}/students/active",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read')")
    @ApiOperation(value = "getAllRooms", notes = "Отправляет всех активных(или нет) студентов, проживавщих(-ющих) в комнате")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<StudentModel>> getAllRoomStudentsActive(@PathVariable Long roomId, @RequestParam(required = false, defaultValue = "true") Boolean is) {
        Optional<HostelRoomModel> o = this.service.getById(roomId);
        return o.map(hostelRoomModel -> new ResponseEntity<>(hostelRoomModel.getStudents().stream().filter(p ->  (p.getStatus().ordinal() == 0) == is).toList(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/rooms/{roomId}/students/approved",
            produces = {"application/json"},
            method = RequestMethod.GET)
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PreAuthorize("#oauth2.hasScope('read')")
    @ApiOperation(value = "getAllRooms", notes = "Отправляет всех подтвержденных(или нет) студентов, проживавщих(-ющих) в комнате")
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_USER", "ROLE_ADMIN"})
    public ResponseEntity<List<StudentModel>> getAllRoomStudentsApproved(@PathVariable Long roomId, @RequestParam(required = false, defaultValue = "true") Boolean is) {
        Optional<HostelRoomModel> o = this.service.getById(roomId);
        return o.map(hostelRoomModel -> new ResponseEntity<>(hostelRoomModel.getStudents().stream().filter(p -> p.isApproved() == is).toList(), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
