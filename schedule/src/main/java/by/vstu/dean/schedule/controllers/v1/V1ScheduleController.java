package by.vstu.dean.schedule.controllers.v1;

import by.vstu.dean.schedule.services.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/schedule")
@RequiredArgsConstructor
public class V1ScheduleController {
    private final ScheduleService scheduleService;

    @RequestMapping(value = "/teacher/{teacherId}"
            , produces = {"application/json"}
            , method = RequestMethod.GET)
    public ResponseEntity<?> getScheduleByTeacher(@PathVariable Long teacherId) {
        return new ResponseEntity<>(this.scheduleService.getSchedulesByTeacher(teacherId), HttpStatus.OK);
    }


    @RequestMapping(value = "/group/{groupId}"
            , produces = {"application/json"}
            , method = RequestMethod.GET)
    public ResponseEntity<?> getScheduleByGroup(@PathVariable Long groupId) {
        return new ResponseEntity<>(this.scheduleService.getSchedulesByGroup(groupId), HttpStatus.OK);
    }

    @RequestMapping(value = "/week"
            , produces = {"application/json"}
            , method = RequestMethod.GET)
    public ResponseEntity<?> getNameOFWeek() {
        return new ResponseEntity<>(this.scheduleService.definitionOfWeek(), HttpStatus.OK);
    }


}
