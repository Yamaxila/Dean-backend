package by.vstu.dean.impl;

import by.vstu.dean.dto.v1.students.InstitutionDTO;
import by.vstu.dean.dto.mapper.InstitutionMapper;
import by.vstu.dean.models.students.InstitutionModel;
import by.vstu.dean.repo.InstitutionModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
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

            if (optionalInstitutionModel.isPresent())
                return optionalInstitutionModel.get();

            InstitutionModel institutionModel = new InstitutionModel();
            institutionModel.setId(dto.getId());
            institutionModel.setSourceId(dto.getSourceId());
            institutionModel.setStatus(dto.getStatus());
            institutionModel.setUpdated(dto.getUpdated());
            institutionModel.setFullName(dto.getFullName());
            institutionModel.setShortName(dto.getShortName());
            return institutionModel;
        }
    }

    public InstitutionDTO toDto(InstitutionModel entity) {
        if (entity == null) {
            return null;
        } else {
            InstitutionDTO institutionDTO = new InstitutionDTO();
            institutionDTO.setId(entity.getId());
            institutionDTO.setSourceId(entity.getSourceId());
            institutionDTO.setUpdated(LocalDateTime.now());
            institutionDTO.setStatus(entity.getStatus());
            institutionDTO.setFullName(entity.getFullName());
            institutionDTO.setShortName(entity.getShortName());
            return institutionDTO;
        }
    }

    public InstitutionModel partialUpdate(InstitutionDTO dto, InstitutionModel entity) {
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
                entity.setUpdated(LocalDateTime.now());
            }

            if (dto.getFullName() != null) {
                entity.setFullName(dto.getFullName());
            }

            if (dto.getShortName() != null) {
                entity.setShortName(dto.getShortName());
            }

            return entity;
        }
    }

    public List<InstitutionDTO> toDto(List<InstitutionModel> all) {
        if (all == null) {
            return null;
        }

        return all.stream().map(this::toDto).toList();
    }

    public List<InstitutionModel> toEntity(List<InstitutionDTO> all) {
        if (all == null) {
            return null;
        }

        return all.stream().map(this::toEntity).toList();
    }

}
