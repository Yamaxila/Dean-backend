package by.vstu.dean.dto.mapper.impl;

import by.vstu.dean.dto.future.students.EducationDTO;
import by.vstu.dean.dto.mapper.EducationMapper;
import by.vstu.dean.future.models.students.EducationModel;
import by.vstu.dean.future.repo.EducationModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class EducationMapperImpl implements EducationMapper {

    @Autowired
    private EducationModelRepository educationModelRepository;

    public EducationModel toEntity(EducationDTO dto) {
        if (dto == null) {
            return null;
        } else {

            Optional<EducationModel> optionalEducationModel = this.educationModelRepository.findById(dto.getId());

            if(optionalEducationModel.isPresent())
                return optionalEducationModel.get();

            EducationModel educationModel = new EducationModel();
            educationModel.setId(dto.getId());
            educationModel.setSourceId(dto.getSourceId());
            educationModel.setStatus(dto.getStatus());
            educationModel.setUpdated(dto.getUpdated());
            educationModel.setEducation(dto.getEducation());
            educationModel.setEducationDocumentType(dto.getEducationDocumentType());
            educationModel.setEducationDocumentSerial(dto.getEducationDocumentSerial());
            educationModel.setEducationDocumentNumber(dto.getEducationDocumentNumber());
            return educationModel;
        }
    }

    public EducationDTO toDto(EducationModel entity) {
        if (entity == null) {
            return null;
        } else {
            EducationDTO educationDTO = new EducationDTO();
            educationDTO.setId(entity.getId());
            educationDTO.setSourceId(entity.getSourceId());
            educationDTO.setUpdated(entity.getUpdated());
            educationDTO.setStatus(entity.getStatus());
            educationDTO.setEducation(entity.getEducation());
            educationDTO.setEducationDocumentType(entity.getEducationDocumentType());
            educationDTO.setEducationDocumentSerial(entity.getEducationDocumentSerial());
            educationDTO.setEducationDocumentNumber(entity.getEducationDocumentNumber());
            return educationDTO;
        }
    }

    public EducationModel partialUpdate(EducationDTO dto, EducationModel entity) {
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

            if (dto.getEducation() != null) {
                entity.setEducation(dto.getEducation());
            }

            if (dto.getEducationDocumentType() != null) {
                entity.setEducationDocumentType(dto.getEducationDocumentType());
            }

            if (dto.getEducationDocumentSerial() != null) {
                entity.setEducationDocumentSerial(dto.getEducationDocumentSerial());
            }

            if (dto.getEducationDocumentNumber() != null) {
                entity.setEducationDocumentNumber(dto.getEducationDocumentNumber());
            }

            return entity;
        }
    }

    public List<EducationDTO> toDto(List<EducationModel> all) {
        if (all == null) {
            return null;
        }

        return all.stream().map(this::toDto).toList();
    }

    public List<EducationModel> toEntity(List<EducationDTO> all) {
        if (all == null) {
            return null;
        }

        return all.stream().map(this::toEntity).toList();
    }
}
