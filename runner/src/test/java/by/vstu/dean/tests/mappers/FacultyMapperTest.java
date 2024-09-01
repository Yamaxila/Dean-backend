package by.vstu.dean.tests.mappers;

import by.vstu.dean.DeanBackendApplication;
import by.vstu.dean.dto.v1.FacultyDTO;
import by.vstu.dean.mapper.v1.FacultyMapper;
import by.vstu.dean.models.FacultyModel;
import by.vstu.dean.services.FacultyService;
import by.vstu.dean.tests.BaseMapperTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes= DeanBackendApplication.class)
public class FacultyMapperTest extends BaseMapperTest<FacultyModel, FacultyDTO, FacultyMapper> {

    @Autowired
    private FacultyService facultyService;

    @Override
    public FacultyModel getNewEntity() {
        return this.facultyService.getRepo().findTopByOrderByIdDesc();
    }

    @Override
    public FacultyDTO getNewDTO() {
        FacultyDTO facultyDTO = new FacultyDTO();
        facultyDTO.setId(this.getNewEntity().getId());
        facultyDTO.setName("Test");
        facultyDTO.setShortName("TestShortName");

        return facultyDTO;
    }
}
