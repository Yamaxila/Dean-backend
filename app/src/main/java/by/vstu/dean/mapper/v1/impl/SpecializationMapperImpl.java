package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.specs.SpecializationDTO;
import by.vstu.dean.mapper.v1.SpecialityMapper;
import by.vstu.dean.mapper.v1.SpecializationMapper;
import by.vstu.dean.models.specs.SpecializationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpecializationMapperImpl implements SpecializationMapper {

    @Autowired
    private SpecialityMapper mapper;

    public SpecializationModel toEntity(SpecializationDTO dto) {
        if (dto == null) {
            return null;
        }
        SpecializationModel specializationModel = (SpecializationModel) ReflectionUtils.mapObject(new SpecializationModel(), dto, true, false);
        specializationModel.setSpec(this.mapper.toEntity(dto.getSpec()));
        return specializationModel;
    }

    public SpecializationDTO toDto(SpecializationModel entity) {
        if (entity == null) {
            return null;
        }
        SpecializationDTO specializationDTO = SpecializationMapper.super.toDto(entity);
        specializationDTO.setSpec(this.mapper.toDto(entity.getSpec()));
        return specializationDTO;
    }
}
