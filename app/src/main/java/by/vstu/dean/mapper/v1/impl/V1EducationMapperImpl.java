package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.V1EducationDTO;
import by.vstu.dean.mapper.v1.V1EducationMapper;
import by.vstu.dean.models.students.internal.EducationModel;
import by.vstu.dean.services.students.EducationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class V1EducationMapperImpl implements V1EducationMapper {

    @Autowired
    private EducationService educationService;

    public EducationModel toEntity(V1EducationDTO dto) {
        if (dto == null) {
            return null;
        }

        EducationModel educationModel = new EducationModel();

        if (dto.getId() != null)
            educationModel = this.educationService.getById(dto.getId()).orElse(new EducationModel());


        return (EducationModel) ReflectionUtils.mapObject(educationModel, dto, true, dto.getId() != null);
    }
}
