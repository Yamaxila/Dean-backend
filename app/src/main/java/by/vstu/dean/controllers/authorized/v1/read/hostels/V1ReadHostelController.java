package by.vstu.dean.controllers.authorized.v1.read.hostels;

import by.vstu.dean.core.controllers.BaseReadController;
import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.trowable.BadRequestException;
import by.vstu.dean.core.trowable.DatabaseFetchException;
import by.vstu.dean.core.trowable.MappingException;
import by.vstu.dean.core.utils.ValidationUtils;
import by.vstu.dean.dto.v1.hostels.V1HostelRoomDTO;
import by.vstu.dean.dto.v1.students.V1StudentDTO;
import by.vstu.dean.mapper.v1.V1HostelRoomMapper;
import by.vstu.dean.mapper.v1.V1StudentMapper;
import by.vstu.dean.models.hostels.HostelRoomModel;
import by.vstu.dean.repo.HostelRoomModelRepository;
import by.vstu.dean.services.HostelRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hostels/")
@Tag(name = "HostelController", description = "Общежития и комнаты")
@PreAuthorize("hasAnyAuthority('ROLE_SERVICE', 'ROLE_METHODIST') AND jwtTokenFilter.hasAnyResourceIds('dean')")
public class V1ReadHostelController extends BaseReadController<V1HostelRoomDTO, HostelRoomModel, V1HostelRoomMapper, HostelRoomModelRepository, HostelRoomService> {

    private final V1StudentMapper studentMapper;

    public V1ReadHostelController(HostelRoomService service, V1HostelRoomMapper mapper, V1StudentMapper studentMapper) {
        super(service, mapper);
        this.studentMapper = studentMapper;
    }

    /**
     * Получает все комнаты указанного общежития.
     *
     * @param id Идентификатор общежития.
     * @return Список комнат указанного общежития.
     */
    @RequestMapping(value = "{id}/rooms", produces = {"application/json"}, method = RequestMethod.GET)
    @Operation(method = "getAllRooms", description = "Отправляет все комнаты указанного общежития")
    public ResponseEntity<List<V1HostelRoomDTO>> findAllByHostelId(@PathVariable int id) {
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
    @Operation(method = "getAllRoomsActive", description = "Отправляет все активные комнаты указанного общежития")
    public ResponseEntity<List<V1HostelRoomDTO>> findAllByHostelIdActive(@PathVariable int id, @RequestParam(required = false, defaultValue = "true") Boolean is) {
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
    @Operation(method = "getAllByHostelAndFloor", description = "Отправляет все активные комнаты указанного общежития по этажу")
    public ResponseEntity<List<V1HostelRoomDTO>> findAllByHostelIdAndFloor(@PathVariable int id, @PathVariable int floor) {
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
    @Operation(method = "getAllRooms", description = "Отправляет комнату по её id")
    public ResponseEntity<V1HostelRoomDTO> getById(@PathVariable Long roomId) {
        return super.getById(roomId);
    }

    /**
     * Получает всех студентов, проживающих в комнате.
     *
     * @param id Идентификатор комнаты.
     * @return Список студентов, проживающих в комнате.
     */
    @RequestMapping(value = "/rooms/{id}/students", produces = {"application/json"}, method = RequestMethod.GET)
    @Operation(method = "getAllRooms", description = "Отправляет всех студентов, проживавших в комнате")
    public ResponseEntity<List<V1StudentDTO>> getAllRoomStudents(@PathVariable Long id) {
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
    @Operation(method = "getAllRooms", description = "Отправляет всех активных(или нет) студентов, проживавших в комнате")
    public ResponseEntity<List<V1StudentDTO>> getAllRoomStudentsActive(@PathVariable Long roomId, @RequestParam(required = false, defaultValue = "true") Boolean is) {
        if (ValidationUtils.isLongValid(roomId))
            throw new BadRequestException("roomId is required");

        Optional<HostelRoomModel> o = this.service.getById(roomId);

        if (o.isEmpty())
            throw new DatabaseFetchException(String.format("Room with id %s not found", roomId));

        List<V1StudentDTO> out = this.studentMapper.toDto(o.get().getStudents().stream().filter(p -> p.getStatus().equals(is ? EStatus.ACTIVE : EStatus.DELETED)).toList());

        if (out == null)
            throw new MappingException("Cannot map HostelRoomModel to V1StudentDTO");

        return ResponseEntity.ok(out);
    }

    /**
     * Получает всех подтвержденных (или нет) студентов, проживающих в комнате.
     *
     * @param roomId Идентификатор комнаты.
     * @param is     Признак подтверждения студентов (по умолчанию true).
     * @return Список подтвержденных (или нет) студентов, проживающих в комнате.
     */
    @RequestMapping(value = "/rooms/{roomId}/students/approved", produces = {"application/json"}, method = RequestMethod.GET)
    @Operation(method = "getAllRooms", description = "Отправляет всех подтвержденных(или нет) студентов, проживавших в комнате")
    public ResponseEntity<List<V1StudentDTO>> getAllRoomStudentsApproved(@PathVariable Long roomId, @RequestParam(required = false, defaultValue = "true") Boolean is) {
        if (ValidationUtils.isLongValid(roomId))
            throw new BadRequestException("roomId is required");

        Optional<HostelRoomModel> o = this.service.getById(roomId);

        if (o.isEmpty())
            throw new DatabaseFetchException(String.format("Room with id %s not found", roomId));

        List<V1StudentDTO> out = this.studentMapper.toDto(o.get().getStudents().stream().filter(p -> p.isApproved() == is).toList());

        if (out == null)
            throw new MappingException("Cannot map HostelRoomModel to V1StudentDTO");


        return ResponseEntity.ok(out);
    }

}