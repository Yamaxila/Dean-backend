package by.vstu.dean.controllers.nonauthorized.v1;

import by.vstu.dean.core.controllers.PublicController;
import by.vstu.dean.dto.v1.pub.students.V1PublicStudentDTO;
import by.vstu.dean.mapper.v1.pub.V1PublicStudentMapper;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.repo.StudentModelRepository;
import by.vstu.dean.services.students.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public/students/")
@Tag(name = "Students", description = "Студенты")
public class V1PublicStudentController extends PublicController<V1PublicStudentDTO, StudentModel, V1PublicStudentMapper, StudentModelRepository, StudentService> {
    public V1PublicStudentController(StudentService service, V1PublicStudentMapper mapper) {
        super(service, mapper);
    }
}
