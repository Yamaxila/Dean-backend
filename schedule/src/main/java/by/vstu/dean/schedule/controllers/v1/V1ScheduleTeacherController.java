package by.vstu.dean.schedule.controllers.v1;

import by.vstu.dean.dto.v1.pub.teachers.V1PublicTeacherDTO;
import by.vstu.dean.schedule.services.ScheduleTeacherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * Контроллер для работы с преподавателями расписания.
 */
@RestController
@RequestMapping("/api/v1/schedule/teacher")
@RequiredArgsConstructor
@Tag(name = "ScheduleTeacher")
public class V1ScheduleTeacherController {
    private final ScheduleTeacherService scheduleTeacherService;

    /**
     * Получение всех преподавателей для расписания.
     *
     * @return Список преподавателей
     */
    @RequestMapping(value = "/all"
            , produces = {"application/json"}
            , method = RequestMethod.GET)
    @Operation(method = "all", description = "Получение всех преподавателей для расписания")
    public ResponseEntity<List<V1PublicTeacherDTO>> getAll() {
        return new ResponseEntity<>(this.scheduleTeacherService.getValidTeacherDTOs(), HttpStatus.OK);
    }
}
