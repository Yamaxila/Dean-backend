package by.vstu.dean.tests.mappers;

import by.vstu.dean.DeanBackendApplication;
import by.vstu.dean.dto.v1.V1FacultyDTO;
import by.vstu.dean.mapper.v1.V1FacultyMapper;
import by.vstu.dean.models.FacultyModel;
import by.vstu.dean.services.FacultyService;
import by.vstu.dean.tests.BaseMapperTest;
import by.vstu.dean.tests.ServicesTest;
import org.javers.core.JaversBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes= DeanBackendApplication.class)
public class FacultyMapperTest extends BaseMapperTest<FacultyModel, V1FacultyDTO, V1FacultyMapper> {

    @Autowired
    private FacultyService facultyService;
    @Autowired
    private ServicesTest servicesTest;

    public FacultyMapperTest() {
        super(JaversBuilder.javers().build());
    }

    @Override
    public FacultyModel getNewEntity() {
        if (this.facultyService.getRepo().count() == 0)
            this.servicesTest.saveFacultyModel();
        return this.facultyService.getRepo().findTopByOrderByIdDesc();
    }

    @Override
    public V1FacultyDTO getNewDTO() {
        V1FacultyDTO facultyDTO = new V1FacultyDTO();
        facultyDTO.setId(this.getNewEntity().getId());
        facultyDTO.setName("Test");
        facultyDTO.setShortName("TestShortName");

        return facultyDTO;
    }
}
