package by.vstu.dean.controllers.v1.pub;

import by.vstu.dean.core.controllers.PublicController;
import by.vstu.dean.dto.v1.pub.students.PublicStudentDTO;
import by.vstu.dean.mapper.v1.pub.PublicStudentMapper;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.repo.StudentModelRepository;
import by.vstu.dean.services.students.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/students/")
public class PublicStudentController extends PublicController<PublicStudentDTO, StudentModel, PublicStudentMapper, StudentModelRepository, StudentService> {
    public PublicStudentController(StudentService service, PublicStudentMapper mapper) {
        super(service, mapper);
    }
}
