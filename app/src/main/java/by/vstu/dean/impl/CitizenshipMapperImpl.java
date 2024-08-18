package by.vstu.dean.impl;

import by.vstu.dean.dto.v1.students.CitizenshipDTO;
import by.vstu.dean.dto.mapper.CitizenshipMapper;
import by.vstu.dean.models.students.CitizenshipModel;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CitizenshipMapperImpl implements CitizenshipMapper {

    public CitizenshipMapperImpl() {
    }

    public CitizenshipModel toEntity(CitizenshipDTO dto) {
        if (dto == null) {
            return null;
        } else {
            CitizenshipModel citizenshipModel = new CitizenshipModel();
            citizenshipModel.setId(dto.getId());
            citizenshipModel.setSourceId(dto.getSourceId());
            citizenshipModel.setStatus(dto.getStatus());
            citizenshipModel.setUpdated(LocalDateTime.now());
            citizenshipModel.setName(dto.getName());
            return citizenshipModel;
        }
    }

    public CitizenshipDTO toDto(CitizenshipModel entity) {
        if (entity == null) {
            return null;
        } else {
            CitizenshipDTO citizenshipDTO = new CitizenshipDTO();
            citizenshipDTO.setId(entity.getId());
            citizenshipDTO.setSourceId(entity.getSourceId());
            citizenshipDTO.setUpdated(entity.getUpdated());
            citizenshipDTO.setStatus(entity.getStatus());
            citizenshipDTO.setName(entity.getName());
            return citizenshipDTO;
        }
    }

    public CitizenshipModel partialUpdate(CitizenshipDTO dto, CitizenshipModel entity) {
        if (dto == null) {
            return null;
        } else {
            if (dto.getId() != null) {
                entity.setId(dto.getId());
            }

            if (dto.getStatus() != null) {
                entity.setStatus(dto.getStatus());
            }

            if (dto.getUpdated() != null) {
                entity.setUpdated(LocalDateTime.now()
                );
            }

            if (dto.getName() != null) {
                entity.setName(dto.getName());
            }

            return entity;
        }
    }

    public List<CitizenshipDTO> toDto(List<CitizenshipModel> all) {
        if (all == null) {
            return null;
        }
        return all.stream().map(this::toDto).toList();
    }

    public List<CitizenshipModel> toEntity(List<CitizenshipDTO> all) {
        if (all == null) {
            return null;
        }

        return all.stream().map(this::toEntity).toList();
    }

}
