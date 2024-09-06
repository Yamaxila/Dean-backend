package by.vstu.dean.tests.mappers;

import by.vstu.dean.DeanBackendApplication;
import by.vstu.dean.dto.v1.specs.QualificationDTO;
import by.vstu.dean.mapper.v1.QualificationMapper;
import by.vstu.dean.models.specs.QualificationModel;
import by.vstu.dean.services.QualificationService;
import by.vstu.dean.tests.BaseMapperTest;
import org.javers.core.JaversBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes= DeanBackendApplication.class)
public class QualificationMapperTest extends BaseMapperTest<QualificationModel, QualificationDTO, QualificationMapper> {

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
    public QualificationDTO getNewDTO() {
        QualificationDTO qualificationDTO = new QualificationDTO();
        qualificationDTO.setId(this.getNewEntity().getId());
        qualificationDTO.setName("New Qualification Name");
        return qualificationDTO;
    }
}
