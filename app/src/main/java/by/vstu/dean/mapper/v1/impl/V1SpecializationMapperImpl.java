package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.specs.V1SpecializationDTO;
import by.vstu.dean.mapper.v1.V1SpecialityMapper;
import by.vstu.dean.mapper.v1.V1SpecializationMapper;
import by.vstu.dean.models.specs.SpecializationModel;
import by.vstu.dean.services.SpecializationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class V1SpecializationMapperImpl implements V1SpecializationMapper {

    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private V1SpecialityMapper mapper;

    public SpecializationModel toEntity(V1SpecializationDTO dto) {
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

    public V1SpecializationDTO toDto(SpecializationModel entity) {
        if (entity == null) {
            return null;
        }
        V1SpecializationDTO specializationDTO = V1SpecializationMapper.super.toDto(entity);
        specializationDTO.setSpec(this.mapper.toDto(entity.getSpec()));

        Pattern pattern = Pattern.compile("-");

        if (specializationDTO.getName() != null && specializationDTO.getSpec() != null) {
            Matcher matcher = pattern.matcher(specializationDTO.getName());
            int i = 0;
            while (matcher.find())
                i++;
            if (i > 5)
                specializationDTO.setName(specializationDTO.getSpec().getName());
        }

        return specializationDTO;
    }


    @Override
    public SpecializationModel partialUpdate(V1SpecializationDTO dto, SpecializationModel entity) {
        if (dto == null)
            return null;

        entity = V1SpecializationMapper.super.partialUpdate(dto, entity);

        if(dto.getSpec() != null)
            entity.setSpec(this.mapper.partialUpdate(dto.getSpec(), entity.getSpec()));

        return entity;
    }
}
