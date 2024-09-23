package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.InstitutionDTO;
import by.vstu.dean.mapper.v1.InstitutionMapper;
import by.vstu.dean.models.students.internal.InstitutionModel;
import by.vstu.dean.services.students.InstitutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InstitutionMapperImpl implements InstitutionMapper {

    @Autowired
    private InstitutionService institutionService;

    public InstitutionModel toEntity(InstitutionDTO dto) {
        if (dto == null) {
            return null;
        }

        InstitutionModel institutionModel = new InstitutionModel();

        if (dto.getId() != null)
            institutionModel = this.institutionService.getById(dto.getId()).orElse(new InstitutionModel());

        return (InstitutionModel) ReflectionUtils.mapObject(institutionModel, dto, true, dto.getId() != null);
    }
}
