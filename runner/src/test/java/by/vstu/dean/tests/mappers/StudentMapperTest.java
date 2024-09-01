package by.vstu.dean.tests.mappers;

import by.vstu.dean.DeanBackendApplication;
import by.vstu.dean.dto.v1.students.StudentDTO;
import by.vstu.dean.mapper.v1.StudentMapper;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.services.StudentService;
import by.vstu.dean.tests.BaseMapperTest;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes= DeanBackendApplication.class)
public class StudentMapperTest extends BaseMapperTest<StudentModel, StudentDTO, StudentMapper> {

    @Autowired
    private StudentService studentService;

    @BeforeEach
    public void init() {
        this.notEqualsFields = new String[] { "id", "phone", "lastDocument" };
    }

    @Override
    public StudentModel getNewEntity() {
        return this.studentService.getRepo().findTopByOrderByIdDesc();
    }

    @Override
    public StudentDTO getNewDTO() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(this.getNewEntity().getId());
        studentDTO.setPhone("New Phone");
        return studentDTO;
    }
}
