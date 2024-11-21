package by.vstu.dean.schedule.controllers.v1;

import by.vstu.dean.schedule.services.ScheduleGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

/**
 * Контроллер для работы с группами расписания.
 */
@RestController
@RequestMapping("/api/v1/schedule/group")
@RequiredArgsConstructor
@Tag(name = "ScheduleGroup")
public class V1ScheduleGroupController {

    private final ScheduleGroupService scheduleGroupService;

    /**
     * Получение всех групп для дневного расписания.
     *
     * @return Список групп
     */
    @RequestMapping(value = "/daytime"
            , produces = {"application/json"}
            , method = RequestMethod.GET)
    @Operation(method = "daytime", description = "Получение всех групп для дневного расписания")
    public ResponseEntity<?> getDaytime() {
        return new ResponseEntity<>(this.scheduleGroupService.getValidGroupDTOs().stream()
                .filter(g -> !(g.getFacultyId() == 1 || g.getFacultyId() == 2)).toList(), HttpStatus.OK);
    }

    /**
     * Получение всех групп для заочного расписания.
     *
     * @return Список групп
     */
    @RequestMapping(value = "/correspondence"
            , produces = {"application/json"}
            , method = RequestMethod.GET)
    @Operation(method = "correspondence", description = "Получение всех групп для заочного расписания")
    public ResponseEntity<?> getCorrespondence() {
        return new ResponseEntity<>(this.scheduleGroupService.getValidGroupDTOs().stream()
                .filter(g -> g.getFacultyId() == 1 || g.getFacultyId() == 2).toList(), HttpStatus.OK);
    }
}
