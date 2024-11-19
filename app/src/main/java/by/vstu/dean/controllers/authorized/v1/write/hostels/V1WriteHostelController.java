package by.vstu.dean.controllers.authorized.v1.write.hostels;

import by.vstu.dean.core.controllers.BaseWriteController;
import by.vstu.dean.dto.v1.hostels.V1HostelRoomDTO;
import by.vstu.dean.mapper.v1.V1HostelRoomMapper;
import by.vstu.dean.models.hostels.HostelRoomModel;
import by.vstu.dean.repo.HostelRoomModelRepository;
import by.vstu.dean.services.HostelRoomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hostels/")
@Tag(name = "HostelController", description = "Общежития и комнаты")
@PreAuthorize("hasAnyAuthority('ROLE_SERVICE', 'ROLE_METHODIST')")
public class V1WriteHostelController extends BaseWriteController<V1HostelRoomDTO, HostelRoomModel, V1HostelRoomMapper, HostelRoomModelRepository, HostelRoomService> {

    public V1WriteHostelController(HostelRoomService service, V1HostelRoomMapper mapper) {
        super(service, mapper);
    }

    /**
     * Прикрепляет студента в комнату.
     *
     * @param roomId    Идентификатор комнаты.
     * @param studentId Идентификатор студента, которого назначают в комнату.
     * @return Комната, в которую назначают студента с ним же.
     */
    @RequestMapping(value = "/rooms/{roomId}/students", produces = {"application/json"}, method = RequestMethod.PUT)
    @Operation(method = "putStudent", description = "Прикрепляет студента к комнате")
    public ResponseEntity<V1HostelRoomDTO> putStudent(@PathVariable Long roomId, @RequestParam Long studentId) {
        Optional<HostelRoomModel> o = this.service.getById(roomId);
        return o.map(hostelRoomModel -> new ResponseEntity<>(this.mapper.toDto(this.service.setStudent(hostelRoomModel, studentId)), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    /**
     * Открепляет студента от комнаты.
     *
     * @param roomId    Идентификатор комнаты.
     * @param studentId Идентификатор студента, которого открепляют от комнаты.
     * @return Комната, от которой открепляют студента без него.
     */
    @RequestMapping(value = "/rooms/{roomId}/students", produces = {"application/json"}, method = RequestMethod.DELETE)
    @Operation(method = "removeStudent", description = "Открепляет студента от комнаты")
    public ResponseEntity<V1HostelRoomDTO> deleteStudent(@PathVariable Long roomId, @RequestParam Long studentId) {
        Optional<HostelRoomModel> o = this.service.getById(roomId);
        return o.map(hostelRoomModel -> new ResponseEntity<>(this.mapper.toDto(this.service.removeStudent(hostelRoomModel, studentId)), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}
