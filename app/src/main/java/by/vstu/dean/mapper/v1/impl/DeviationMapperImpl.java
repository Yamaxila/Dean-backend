package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.dto.v1.students.DeviationDTO;
import by.vstu.dean.mapper.v1.DeviationMapper;
import by.vstu.dean.models.students.DeviationModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeviationMapperImpl implements DeviationMapper {
    @Override
    public DeviationModel toEntity(DeviationDTO dto) {
        return null;
    }

    @Override
    public DeviationDTO toDto(DeviationModel entity) {
        return null;
    }

    @Override
    public DeviationModel partialUpdate(DeviationDTO dto, DeviationModel entity) {
        return null;
    }

    @Override
    public List<DeviationDTO> toDto(List<DeviationModel> all) {
        return null;
    }

    @Override
    public List<DeviationModel> toEntity(List<DeviationDTO> all) {
        return null;
    }
}