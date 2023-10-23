package by.vstu.dean.dto.mapper.impl;

import by.vstu.dean.dto.future.students.StudentLanguageDTO;
import by.vstu.dean.dto.mapper.StudentLanguageMapper;
import by.vstu.dean.future.models.students.StudentLanguageModel;
import by.vstu.dean.future.repo.StudentLanguageModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class StudentLanguageMapperImpl implements StudentLanguageMapper {

    @Autowired
    private StudentLanguageModelRepository studentLanguageService;

    public StudentLanguageModel toEntity(StudentLanguageDTO dto) {
        if (dto == null) {
            return null;
        } else {
            Optional<StudentLanguageModel> optionalStudentLanguageModel = this.studentLanguageService.findById(dto.getId());

            if (optionalStudentLanguageModel.isPresent())
                return optionalStudentLanguageModel.get();

            StudentLanguageModel studentLanguageModel = new StudentLanguageModel();
            studentLanguageModel.setId(dto.getId());
            studentLanguageModel.setSourceId(dto.getSourceId());
            studentLanguageModel.setStatus(dto.getStatus());
            studentLanguageModel.setUpdated(LocalDateTime.now());
            studentLanguageModel.setName(dto.getName());
            return studentLanguageModel;
        }
    }

    public StudentLanguageDTO toDto(StudentLanguageModel entity) {
        if (entity == null) {
            return null;
        } else {
            StudentLanguageDTO studentLanguageDTO = new StudentLanguageDTO();
            studentLanguageDTO.setId(entity.getId());
            studentLanguageDTO.setSourceId(entity.getSourceId());
            studentLanguageDTO.setUpdated(entity.getUpdated());
            studentLanguageDTO.setStatus(entity.getStatus());
            studentLanguageDTO.setName(entity.getName());
            return studentLanguageDTO;
        }
    }

    public StudentLanguageModel partialUpdate(StudentLanguageDTO dto, StudentLanguageModel entity) {
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

    public List<StudentLanguageDTO> toDto(List<StudentLanguageModel> all) {
        if (all == null) {
            return null;
        }

        return all.stream().map(this::toDto).toList();
    }

    public List<StudentLanguageModel> toEntity(List<StudentLanguageDTO> all) {
        if (all == null) {
            return null;
        }
        return all.stream().map(this::toEntity).toList();
    }

}
