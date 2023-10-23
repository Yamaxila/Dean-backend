package by.vstu.dean.dto.mapper.impl;

import by.vstu.dean.dto.future.specs.QualificationDTO;
import by.vstu.dean.dto.mapper.QualificationMapper;
import by.vstu.dean.future.models.specs.QualificationModel;
import by.vstu.dean.future.repo.QualificationModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class QualificationMapperImpl implements QualificationMapper {

    @Autowired
    private QualificationModelRepository qualificationModelRepository;

    public QualificationModel toEntity(QualificationDTO dto) {
        if (dto == null) {
            return null;
        } else {

            Optional<QualificationModel> optionalQualification = this.qualificationModelRepository.findById(dto.getId());

            if(optionalQualification.isPresent())
                return optionalQualification.get();

            QualificationModel qualificationModel = new QualificationModel();
            qualificationModel.setId(dto.getId());
            qualificationModel.setSourceId(dto.getSourceId());
            qualificationModel.setStatus(dto.getStatus());
            qualificationModel.setUpdated(dto.getUpdated());
            qualificationModel.setName(dto.getName());
            qualificationModel.setNameGenitive(dto.getNameGenitive());
            return qualificationModel;
        }
    }

    public QualificationDTO toDto(QualificationModel entity) {
        if (entity == null) {
            return null;
        } else {
            QualificationDTO qualificationDTO = new QualificationDTO();
            qualificationDTO.setId(entity.getId());
            qualificationDTO.setSourceId(entity.getSourceId());
            qualificationDTO.setUpdated(entity.getUpdated());
            qualificationDTO.setStatus(entity.getStatus());
            qualificationDTO.setName(entity.getName());
            qualificationDTO.setNameGenitive(entity.getNameGenitive());
            return qualificationDTO;
        }
    }

    public QualificationModel partialUpdate(QualificationDTO dto, QualificationModel entity) {
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
                entity.setUpdated(dto.getUpdated());
            }

            if (dto.getName() != null) {
                entity.setName(dto.getName());
            }

            if (dto.getNameGenitive() != null) {
                entity.setNameGenitive(dto.getNameGenitive());
            }

            return entity;
        }
    }

    public List<QualificationDTO> toDto(List<QualificationModel> all) {
        if (all == null) {
            return null;
        }
        return all.stream().map(this::toDto).toList();
    }

    public List<QualificationModel> toEntity(List<QualificationDTO> all) {
        if (all == null) {
            return null;
        }
        return all.stream().map(this::toEntity).toList();
    }

}
