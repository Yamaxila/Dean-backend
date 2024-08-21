package by.vstu.dean.impl;

import by.vstu.dean.dto.mapper.ExamTypeMapper;
import by.vstu.dean.dto.v1.lessons.ExamTypeDTO;
import by.vstu.dean.models.lessons.ExamModel;
import by.vstu.dean.repo.ExamModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class ExamTypeMapperImpl implements ExamTypeMapper {

    @Autowired
    private ExamModelRepository examModelRepository;

    public ExamModel toEntity(ExamTypeDTO dto) {
        if (dto == null) {
            return null;
        } else {

            Optional<ExamModel> optionalExamModel = this.examModelRepository.findById(dto.getId());

            if (optionalExamModel.isPresent())
                return optionalExamModel.get();

            ExamModel examModel = new ExamModel();
            examModel.setId(dto.getId());
            examModel.setSourceId(dto.getSourceId());
            examModel.setStatus(dto.getStatus());
            examModel.setUpdated(dto.getUpdated());
            return examModel;
        }
    }

    public ExamTypeDTO toDto(ExamModel entity) {
        if (entity == null) {
            return null;
        } else {
            ExamTypeDTO examTypeDTO = new ExamTypeDTO();
            examTypeDTO.setId(entity.getId());
            examTypeDTO.setSourceId(entity.getSourceId());
            examTypeDTO.setUpdated(entity.getUpdated());
            examTypeDTO.setStatus(entity.getStatus());
            return examTypeDTO;
        }
    }

    public ExamModel partialUpdate(ExamTypeDTO dto, ExamModel entity) {
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

            return entity;
        }
    }

    public List<ExamTypeDTO> toDto(List<ExamModel> all) {
        if (all == null) {
            return null;
        }
        return all.stream().map(this::toDto).toList();
    }

    public List<ExamModel> toEntity(List<ExamTypeDTO> all) {
        if (all == null) {
            return null;
        }
        return all.stream().map(this::toEntity).toList();
    }

}
