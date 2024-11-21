package by.vstu.dean.timetable.controllers.v1.read;

import by.vstu.dean.core.controllers.BaseReadController;
import by.vstu.dean.timetable.dto.LessonDTO;
import by.vstu.dean.timetable.dto.mapper.LessonMapper;
import by.vstu.dean.timetable.models.LessonModel;
import by.vstu.dean.timetable.repo.LessonModelRepository;
import by.vstu.dean.timetable.service.LessonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Контроллер для работы с занятиями.
 */
@RestController
@RequestMapping("/api/v1/lessons")
@PreAuthorize("hasAnyRole('ROLE_SCHEDULE', 'ROLE_SERVICE')")
@Tag(name = "Lessons", description = "Занятия")
public class V1LessonController extends BaseReadController<LessonDTO, LessonModel, LessonMapper, LessonModelRepository, LessonService> {

    public V1LessonController(LessonService service, LessonMapper mapper) {
        super(service, mapper);
    }

    @RequestMapping(
            value = {"/dto"},
            produces = {"application/json"},
            method = {RequestMethod.GET})
    @Operation(method = "getAllDTOs", description = "Отправляет все объекты из базы в виде DTO",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Объекты найдены."),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права read у клиента.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"read", "ROLE_SCHEDULE", "ROLE_SERVICE"})
    )
    public ResponseEntity<List<LessonDTO>> getAllDTos() {
        return new ResponseEntity<>(this.mapper.toDto(this.service.getAll()), HttpStatus.OK);
    }

//    @RequestMapping(
//            value = {"/entity"},
//            produces = {"application/json"},
//            method = {RequestMethod.GET})
//    public ResponseEntity<List<LessonModel>> getAllEntities() {
//        return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
//    }

    @RequestMapping(
            value = {"/search"},
            produces = {"application/json"},
            method = {RequestMethod.GET})
    @Operation(method = "getByGroupAndDisciplineAndTeacher", description = "Отправляет все объекты из базы со статусом \"ACTIVE\", отфильтрованные по группе, дисциплине и преподавателю, в виде DTO",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Объекты найдены."),
                    @ApiResponse(responseCode = "401", description = "Не переданы данные авторизации (токен).", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "403", description = "Доступ запрещен. Возможно, не хватает права read у клиента.", content = @Content(schema = @Schema)),
                    @ApiResponse(responseCode = "500", description = "Внутрення ошибка сервера. Смотри логи", content = @Content(schema = @Schema))
            },
            security = @SecurityRequirement(name = "controllers", scopes = {"read", "ROLE_SCHEDULE", "ROLE_SERVICE"})
    )
    public ResponseEntity<List<LessonDTO>> getByGroupAndDisciplineAndTeacher(@RequestParam(name = "gId") Long groupId, @RequestParam(name = "dId") Long disciplineId, @RequestParam(name = "tId") Long teacherId) {
        return new ResponseEntity<>(this.mapper.toDto(this.service.getByGroupDisciplineTeacher(groupId, disciplineId, teacherId)), HttpStatus.OK);
    }

}
