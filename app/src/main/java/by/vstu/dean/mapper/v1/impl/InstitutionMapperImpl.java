package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.InstitutionDTO;
import by.vstu.dean.mapper.v1.InstitutionMapper;
import by.vstu.dean.models.students.InstitutionModel;
import by.vstu.dean.repo.InstitutionModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class InstitutionMapperImpl implements InstitutionMapper {

    @Autowired
    private InstitutionModelRepository institutionModelRepository;

    public InstitutionModel toEntity(InstitutionDTO dto) {
        if (dto == null) {
            return null;
        } else {

            Optional<InstitutionModel> optionalInstitutionModel = this.institutionModelRepository.findById(dto.getId());
            InstitutionModel institutionModel = new InstitutionModel();

            if (optionalInstitutionModel.isPresent())
                institutionModel = optionalInstitutionModel.get();

            return (InstitutionModel) ReflectionUtils.mapObject(institutionModel, dto, true, optionalInstitutionModel.isPresent());
        }
    }
}
