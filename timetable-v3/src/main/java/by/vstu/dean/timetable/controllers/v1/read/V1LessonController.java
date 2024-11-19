package by.vstu.dean.timetable.controllers.v1.read;

import by.vstu.dean.core.controllers.BaseReadController;
import by.vstu.dean.timetable.dto.LessonDTO;
import by.vstu.dean.timetable.dto.mapper.LessonMapper;
import by.vstu.dean.timetable.models.LessonModel;
import by.vstu.dean.timetable.repo.LessonModelRepository;
import by.vstu.dean.timetable.service.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/lessons/")
@PreAuthorize("hasAnyRole('ROLE_SCHEDULE', 'ROLE_SERVICE')")
public class V1LessonController extends BaseReadController<LessonDTO, LessonModel, LessonMapper, LessonModelRepository, LessonService> {

    public V1LessonController(LessonService service, LessonMapper mapper) {
        super(service, mapper);
    }

    @RequestMapping(
            value = {"/dto"},
            produces = {"application/json"},
            method = {RequestMethod.GET})
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
    public ResponseEntity<List<LessonDTO>> getByGroupAndDisciplineAndTeacher(@RequestParam(name = "gId") Long groupId, @RequestParam(name = "dId") Long disciplineId, @RequestParam(name = "tId") Long teacherId) {
        return new ResponseEntity<>(this.mapper.toDto(this.service.getByGroupDisciplineTeacher(groupId, disciplineId, teacherId)), HttpStatus.OK);
    }

}
