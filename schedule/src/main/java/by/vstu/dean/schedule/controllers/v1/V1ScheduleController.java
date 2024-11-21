package by.vstu.dean.schedule.controllers.v1;

import by.vstu.dean.schedule.services.ScheduleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для получения расписания.
 */
@RestController
@RequestMapping("/api/v1/schedule")
@RequiredArgsConstructor
@Tag(name = "Schedule")
public class V1ScheduleController {
    private final ScheduleService scheduleService;

    /**
     * Получение расписания для преподавателя.
     *
     * @param teacherId id преподавателя
     * @return Расписание
     */
    @RequestMapping(value = "/teacher/{teacherId}"
            , produces = {"application/json"}
            , method = RequestMethod.GET
            , params = {"teacherId"})
    @Operation(method = "teacher", description = "Получение расписания для преподавателя")
    public ResponseEntity<?> getScheduleByTeacher(@PathVariable Long teacherId) {
        return new ResponseEntity<>(this.scheduleService.getSchedulesByTeacher(teacherId), HttpStatus.OK);
    }

    /**
     * Получение расписания для группы.
     *
     * @param groupId id группы
     * @return Расписание
     */
    @RequestMapping(value = "/group/{groupId}"
            , produces = {"application/json"}
            , method = RequestMethod.GET
            , params = {"groupId"})
    @Operation(method = "group", description = "Получение расписания для группы")
    public ResponseEntity<?> getScheduleByGroup(@PathVariable Long groupId) {
        return new ResponseEntity<>(this.scheduleService.getSchedulesByGroup(groupId), HttpStatus.OK);
    }

    /**
     * Получение данных о текущей неделе.
     *
     * @return Данные недели
     */
    @RequestMapping(value = "/week"
            , produces = {"application/json"}
            , method = RequestMethod.GET)
    @Operation(method = "groupId", description = "Получение данных о текущей неделу")
    public ResponseEntity<?> getNameOFWeek() {
        return new ResponseEntity<>(this.scheduleService.definitionOfWeek(), HttpStatus.OK);
    }


}
