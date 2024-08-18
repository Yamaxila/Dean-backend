package by.vstu.dean.impl;

import by.vstu.dean.dto.v1.lessons.AbsenceDTO;
import by.vstu.dean.dto.mapper.AbsenceMapper;
import by.vstu.dean.models.lessons.AbsenceModel;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AbsenceMapperImpl implements AbsenceMapper {

    @Override
    public AbsenceModel toEntity(AbsenceDTO dto) {
        if (dto == null) {
            return null;
        }

        AbsenceModel absenceModel = new AbsenceModel();

        absenceModel.setId(dto.getId());
        absenceModel.setSourceId(dto.getSourceId());
        absenceModel.setStatus(dto.getStatus());
        absenceModel.setUpdated(dto.getUpdated());

        return absenceModel;
    }

    @Override
    public AbsenceDTO toDto(AbsenceModel entity) {
        if (entity == null) {
            return null;
        }

        AbsenceDTO absenceDTO = new AbsenceDTO();

        absenceDTO.setId(entity.getId());
        absenceDTO.setSourceId(entity.getSourceId());
        absenceDTO.setUpdated(entity.getUpdated());
        absenceDTO.setStatus(entity.getStatus());

        return absenceDTO;
    }

    @Override
    public AbsenceModel partialUpdate(AbsenceDTO dto, AbsenceModel entity) {
        if (dto == null) {
            return null;
        }

        if (dto.getId() != null) {
            entity.setId(dto.getId());
        }
        if (dto.getStatus() != null) {
            entity.setStatus(dto.getStatus());
        }
        if (dto.getUpdated() != null) {
            entity.setUpdated(dto.getUpdated());
        }

        return entity;
    }

    @Override
    public List<AbsenceDTO> toDto(List<AbsenceModel> all) {
        if (all == null) {
            return null;
        }

        List<AbsenceDTO> list = new ArrayList<>(all.size());
        for (AbsenceModel absenceModel : all) {
            list.add(toDto(absenceModel));
        }

        return list;
    }

    @Override
    public List<AbsenceModel> toEntity(List<AbsenceDTO> all) {
        if (all == null) {
            return null;
        }

        List<AbsenceModel> list = new ArrayList<>(all.size());
        for (AbsenceDTO absenceDTO : all) {
            list.add(toEntity(absenceDTO));
        }

        return list;
    }
}
