package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.dto.v1.students.V1DeviationDTO;
import by.vstu.dean.mapper.v1.V1DeviationMapper;
import by.vstu.dean.models.students.DeviationModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class V1DeviationMapperImpl implements V1DeviationMapper {
    @Override
    public DeviationModel toEntity(V1DeviationDTO dto) {
        return null;
    }

    @Override
    public V1DeviationDTO toDto(DeviationModel entity) {
        return null;
    }

    @Override
    public DeviationModel partialUpdate(V1DeviationDTO dto, DeviationModel entity) {
        return null;
    }

    @Override
    public List<V1DeviationDTO> toDto(List<DeviationModel> all) {
        return null;
    }

    @Override
    public List<DeviationModel> toEntity(List<V1DeviationDTO> all) {
        return null;
    }
}
