package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.EducationDTO;
import by.vstu.dean.mapper.v1.EducationMapper;
import by.vstu.dean.models.students.EducationModel;
import by.vstu.dean.repo.EducationModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class EducationMapperImpl implements EducationMapper {

    @Autowired
    private EducationModelRepository educationModelRepository;

    public EducationModel toEntity(EducationDTO dto) {
        if (dto == null) {
            return null;
        }

        Optional<EducationModel> optionalEducationModel = this.educationModelRepository.findById(dto.getId());
        EducationModel educationModel = new EducationModel();

        if (optionalEducationModel.isPresent())
            educationModel = optionalEducationModel.get();


        return (EducationModel) ReflectionUtils.mapObject(educationModel, dto, true, optionalEducationModel.isPresent());
    }
}
