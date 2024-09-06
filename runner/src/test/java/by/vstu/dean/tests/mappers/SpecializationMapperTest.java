package by.vstu.dean.tests.mappers;

import by.vstu.dean.DeanBackendApplication;
import by.vstu.dean.dto.v1.specs.SpecializationDTO;
import by.vstu.dean.mapper.v1.SpecializationMapper;
import by.vstu.dean.models.specs.SpecializationModel;
import by.vstu.dean.services.SpecializationService;
import by.vstu.dean.tests.BaseMapperTest;
import org.javers.core.JaversBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = DeanBackendApplication.class)
public class SpecializationMapperTest extends BaseMapperTest<SpecializationModel, SpecializationDTO, SpecializationMapper> {

    @Autowired
    private SpecializationService specializationService;

    public SpecializationMapperTest() {
        super(JaversBuilder.javers().build());
    }

    @Override
    public SpecializationModel getNewEntity() {
        return this.specializationService.getRepo().findTopByOrderByIdDesc();
    }

    @Override
    public SpecializationDTO getNewDTO() {
        SpecializationDTO specializationDTO = new SpecializationDTO();
        specializationDTO.setId(this.getNewEntity().getId());
        specializationDTO.setSpezCode("New Spec Code");

        return specializationDTO;
    }
}
