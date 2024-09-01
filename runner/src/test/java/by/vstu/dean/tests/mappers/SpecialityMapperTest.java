package by.vstu.dean.tests.mappers;

import by.vstu.dean.DeanBackendApplication;
import by.vstu.dean.dto.v1.specs.SpecialityDTO;
import by.vstu.dean.mapper.v1.SpecialityMapper;
import by.vstu.dean.models.specs.SpecialityModel;
import by.vstu.dean.services.SpecialityService;
import by.vstu.dean.tests.BaseMapperTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes= DeanBackendApplication.class)
public class SpecialityMapperTest extends BaseMapperTest<SpecialityModel, SpecialityDTO, SpecialityMapper> {
    @Autowired
    private SpecialityService specialityService;

    public SpecialityMapperTest() {
        this.notEqualsFields = new String[] {"id", "specCode"};
    }

    @Override
    public SpecialityModel getNewEntity() {
        return specialityService.getRepo().findTopByOrderByIdDesc();
    }

    @Override
    public SpecialityDTO getNewDTO() {
        SpecialityDTO specialityDTO = new SpecialityDTO();
        specialityDTO.setId(this.getNewEntity().getId());
        specialityDTO.setSpecCode("New Spec Code");
        return null;
    }
}
