package by.vstu.dean.controllers.repo.v1;

import by.vstu.dean.core.controllers.RepoController;
import by.vstu.dean.dto.v1.students.V1StudentDTO;
import by.vstu.dean.mapper.v1.V1StudentMapper;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.repo.StudentModelRepository;
import by.vstu.dean.services.students.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/repo/students/")
public class V1RepoStudentController extends RepoController<V1StudentDTO, StudentModel, V1StudentMapper, StudentModelRepository, StudentService> {
    public V1RepoStudentController(StudentService service, V1StudentMapper mapper) {
        super(service, mapper);
    }
}
