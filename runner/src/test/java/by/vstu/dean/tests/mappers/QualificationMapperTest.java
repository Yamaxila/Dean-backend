package by.vstu.dean.tests.mappers;

import by.vstu.dean.DeanBackendApplication;
import by.vstu.dean.dto.v1.specs.V1QualificationDTO;
import by.vstu.dean.mapper.v1.V1QualificationMapper;
import by.vstu.dean.models.specs.QualificationModel;
import by.vstu.dean.services.QualificationService;
import by.vstu.dean.tests.BaseMapperTest;
import org.javers.core.JaversBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes= DeanBackendApplication.class)
public class QualificationMapperTest extends BaseMapperTest<QualificationModel, V1QualificationDTO, V1QualificationMapper> {

    @Autowired
    private QualificationService qualificationService;

    public QualificationMapperTest() {
        super(JaversBuilder.javers().build());
    }

    @Override
    public QualificationModel getNewEntity() {
        return this.qualificationService.getRepo().findTopByOrderByIdDesc();
    }

    @Override
    public V1QualificationDTO getNewDTO() {
        V1QualificationDTO qualificationDTO = new V1QualificationDTO();
        qualificationDTO.setId(this.getNewEntity().getId());
        qualificationDTO.setName("New Qualification Name");
        return qualificationDTO;
    }
}
