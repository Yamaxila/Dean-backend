package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.specs.SpecializationDTO;
import by.vstu.dean.mapper.v1.SpecialityMapper;
import by.vstu.dean.mapper.v1.SpecializationMapper;
import by.vstu.dean.models.specs.SpecializationModel;
import by.vstu.dean.services.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SpecializationMapperImpl implements SpecializationMapper {

    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private SpecialityMapper mapper;

    public SpecializationModel toEntity(SpecializationDTO dto) {
        if (dto == null) {
            return null;
        }

        SpecializationModel specializationModel = new SpecializationModel();

        if(dto.getId() != null)
            specializationModel = this.specializationService.getById(dto.getId()).orElse(new SpecializationModel());

        specializationModel = (SpecializationModel) ReflectionUtils.mapObject(specializationModel, dto, true, dto.getId() != null);
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


    @Override
    public SpecializationModel partialUpdate(SpecializationDTO dto, SpecializationModel entity) {
        if (dto == null)
            return null;

        entity = SpecializationMapper.super.partialUpdate(dto, entity);

        if(dto.getSpec() != null)
            entity.setSpec(this.mapper.partialUpdate(dto.getSpec(), entity.getSpec()));

        return entity;
    }
}
