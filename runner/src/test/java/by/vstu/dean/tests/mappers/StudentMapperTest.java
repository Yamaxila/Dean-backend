package by.vstu.dean.tests.mappers;

import by.vstu.dean.DeanBackendApplication;
import by.vstu.dean.dto.v1.students.StudentDTO;
import by.vstu.dean.mapper.v1.StudentMapper;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.services.students.StudentService;
import by.vstu.dean.tests.BaseMapperTest;
import by.vstu.dean.tests.ServicesTest;
import org.javers.core.JaversBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes= DeanBackendApplication.class)
public class StudentMapperTest extends BaseMapperTest<StudentModel, StudentDTO, StudentMapper> {

    @Autowired
    private StudentService studentService;
    @Autowired
    private ServicesTest servicesTest;

    public StudentMapperTest() {
        super(JaversBuilder.javers().build());
    }

    @BeforeEach
    public void init() {

        this.notEqualsFields = new String[]{};
    }

    @Override
    public StudentModel getNewEntity() {
        if (this.studentService.getRepo().count() == 0) {

            this.servicesTest.deleteAll();
            this.servicesTest.saveCitizenshipModel();
            this.servicesTest.saveStudentLanguageModel();
            this.servicesTest.saveFacultyModel();
            this.servicesTest.saveDepartmentModel();
            this.servicesTest.saveSpecialityModel();
            this.servicesTest.saveQualificationModel();
            this.servicesTest.saveSpecializationModel();
            this.servicesTest.saveGroupModel();
//            this.servicesTest.saveDocumentModel();
            this.servicesTest.saveStudentModel();
        }

        return this.studentService.getRepo().findTopByOrderByIdDesc();
    }

    @Override
    public StudentDTO getNewDTO() {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(this.getNewEntity().getId());
        studentDTO.setPhone("New Phone");
        studentDTO.setSurname("New Surname");
        return studentDTO;
    }
}
