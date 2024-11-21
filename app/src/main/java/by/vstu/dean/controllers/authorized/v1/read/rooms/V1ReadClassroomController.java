package by.vstu.dean.controllers.authorized.v1.read.rooms;

import by.vstu.dean.core.controllers.BaseReadController;
import by.vstu.dean.dto.v1.rooms.V1ClassroomDTO;
import by.vstu.dean.mapper.v1.V1ClassroomMapper;
import by.vstu.dean.models.rooms.ClassroomModel;
import by.vstu.dean.repo.ClassroomModelRepository;
import by.vstu.dean.services.ClassroomService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер для работы с аудиториями (classrooms).
 *
 * @apiNote Этот контроллер обрабатывает запросы, связанные с аудиториями.
 */
@RestController
@RequestMapping("/api/v1/classes")
@Tag(name = "Classrooms", description = "Аудитории")
@PreAuthorize("hasAnyAuthority('ROLE_SERVICE', 'ROLE_METHODIST')")
public class V1ReadClassroomController extends BaseReadController<V1ClassroomDTO, ClassroomModel, V1ClassroomMapper, ClassroomModelRepository, ClassroomService> {
    /**
     * Конструктор контроллера аудиторий.
     *
     * @param service Сервис для работы с аудиториями.
     * @param mapper Сервис для работы с аудиториями.
     */
    public V1ReadClassroomController(ClassroomService service, V1ClassroomMapper mapper) {
        super(service, mapper);
    }

    /**
     * Метод для обновления данных из файла.
     *
     * <p>Этот метод обновляет данные о аудиториях из файла "rooms.xlsx" и возвращает список обновленных аудиторий.
     *
     * @return Список обновленных аудиторий.
     */
    @RequestMapping(value = "/update", produces = {"application/json"}, method = RequestMethod.POST)
    @Operation(method = "update", description = "Обновляет данные из базы", hidden = true)
    public ResponseEntity<List<ClassroomModel>> updateRoomListFromFile() {
        return new ResponseEntity<>(this.service.updateFromExcel("./rooms.xlsx"), HttpStatus.OK);
    }
}

