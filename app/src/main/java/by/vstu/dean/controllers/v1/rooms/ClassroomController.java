package by.vstu.dean.controllers.v1.rooms;

import by.vstu.dean.core.anotations.ApiSecurity;
import by.vstu.dean.core.controllers.BaseController;
import by.vstu.dean.dto.v1.rooms.ClassroomDTO;
import by.vstu.dean.mapper.v1.ClassroomMapper;
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
@RequestMapping("/api/v1/classes/")
@Tag(name = "Classrooms", description = "Аудитории")
public class ClassroomController extends BaseController<ClassroomDTO, ClassroomModel, ClassroomMapper, ClassroomModelRepository, ClassroomService> {
    /**
     * Конструктор контроллера аудиторий.
     *
     * @param service Сервис для работы с аудиториями.
     * @param mapper Сервис для работы с аудиториями.
     */
    public ClassroomController(ClassroomService service, ClassroomMapper mapper) {
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
    @PreAuthorize("#oauth2.hasScope('write') AND (hasAnyRole('ROLE_ADMIN'))")
    @Operation(method = "update", description = "Обновляет данные из базы", hidden = true)
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<List<ClassroomModel>> updateRoomListFromFile() {
        return new ResponseEntity<>(this.service.updateFromExcel("./rooms.xlsx"), HttpStatus.OK);
    }
}

