package by.vstu.dean.controllers.rooms;

import by.vstu.dean.anotations.ApiSecurity;
import by.vstu.dean.controllers.common.BaseController;
import by.vstu.dean.dto.future.rooms.ClassroomDTO;
import by.vstu.dean.dto.mapper.ClassroomMapper;
import by.vstu.dean.future.models.rooms.ClassroomModel;
import by.vstu.dean.future.repo.ClassroomModelRepository;
import by.vstu.dean.services.ClassroomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/api/classes/")
@Api(tags = "Classrooms", description = "Аудитории")
public class ClassroomController extends BaseController<ClassroomDTO, ClassroomModel, ClassroomMapper, ClassroomModelRepository, ClassroomService> {
    /**
     * Конструктор контроллера аудиторий.
     *
     * @param service Сервис для работы с аудиториями.
     */
    public ClassroomController(ClassroomService service) {
        super(service);
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
    @ApiOperation(value = "update", notes = "Обновляет данные из базы", hidden = true)
    @ApiSecurity(scopes = {"read"}, roles = {"ROLE_ADMIN"})
    public ResponseEntity<List<ClassroomModel>> updateRoomListFromFile() {
        return new ResponseEntity<>(this.service.updateFromExcel("./rooms.xlsx"), HttpStatus.OK);
    }
}

