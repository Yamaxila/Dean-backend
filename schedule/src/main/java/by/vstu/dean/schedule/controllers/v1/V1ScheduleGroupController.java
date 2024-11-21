package by.vstu.dean.schedule.controllers.v1;

import by.vstu.dean.schedule.services.ScheduleGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/schedule/group")
@RequiredArgsConstructor
public class V1ScheduleGroupController {

    private final ScheduleGroupService scheduleGroupService;

    @RequestMapping(value = "/daytime"
            , produces = {"application/json"}
            , method = RequestMethod.GET)
    public ResponseEntity<?> getDaytime() {
        return new ResponseEntity<>(this.scheduleGroupService.getValidGroupDTOs().stream()
                .filter(g -> !(g.getFacultyId() == 1 || g.getFacultyId() == 2)).toList(), HttpStatus.OK);
    }

    @RequestMapping(value = "/correspondence"
            , produces = {"application/json"}
            , method = RequestMethod.GET)
    public ResponseEntity<?> getCorrespondence() {
        return new ResponseEntity<>(this.scheduleGroupService.getValidGroupDTOs().stream()
                .filter(g -> g.getFacultyId() == 1 || g.getFacultyId() == 2).toList(), HttpStatus.OK);
    }
}
