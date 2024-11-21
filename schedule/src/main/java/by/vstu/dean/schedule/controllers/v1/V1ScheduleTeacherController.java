package by.vstu.dean.schedule.controllers.v1;

import by.vstu.dean.schedule.services.ScheduleTeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/schedule/teacher")
@RequiredArgsConstructor
public class V1ScheduleTeacherController {
    private final ScheduleTeacherService scheduleTeacherService;

    @RequestMapping(value = "/all"
            , produces = {"application/json"}
            , method = RequestMethod.GET)
    public ResponseEntity<?> getAll() {
        return new ResponseEntity<>(this.scheduleTeacherService.getValidTeacherDTOs(), HttpStatus.OK);
    }
}
