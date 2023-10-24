package by.vstu.dean.dto.mapper.impl;

import by.vstu.dean.dto.future.lessons.TeacherDegreeDTO;
import by.vstu.dean.dto.mapper.TeacherDegreeMapper;
import by.vstu.dean.future.models.lessons.TeacherDegreeModel;
import by.vstu.dean.future.repo.TeacherDegreeModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class TeacherDegreeMapperImpl implements TeacherDegreeMapper {

    @Autowired
    private TeacherDegreeModelRepository teacherDegreeModelRepository;

    public TeacherDegreeModel toEntity(TeacherDegreeDTO dto) {
        if (dto == null) {
            return null;
        } else {

            Optional<TeacherDegreeModel> optionalTeacherDegreeModel = this.teacherDegreeModelRepository.findById(dto.getId());

            if (optionalTeacherDegreeModel.isPresent())
                return optionalTeacherDegreeModel.get();

            TeacherDegreeModel teacherDegreeModel = new TeacherDegreeModel();
            teacherDegreeModel.setId(dto.getId());
            teacherDegreeModel.setSourceId(dto.getSourceId());
            teacherDegreeModel.setStatus(dto.getStatus());
            teacherDegreeModel.setUpdated(LocalDateTime.now());
            teacherDegreeModel.setName(dto.getName());
            teacherDegreeModel.setHourPrice(dto.getHourPrice());
            return teacherDegreeModel;
        }
    }

    public TeacherDegreeDTO toDto(TeacherDegreeModel entity) {
        if (entity == null) {
            return null;
        } else {
            TeacherDegreeDTO teacherDegreeDTO = new TeacherDegreeDTO();
            teacherDegreeDTO.setId(entity.getId());
            teacherDegreeDTO.setSourceId(entity.getSourceId());
            teacherDegreeDTO.setUpdated(entity.getUpdated());
            teacherDegreeDTO.setStatus(entity.getStatus());
            teacherDegreeDTO.setName(entity.getName());
            teacherDegreeDTO.setHourPrice(entity.getHourPrice());

            return teacherDegreeDTO;
        }
    }

    public TeacherDegreeModel partialUpdate(TeacherDegreeDTO dto, TeacherDegreeModel entity) {
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

            if (dto.getName() != null) {
                entity.setName(dto.getName());
            }

            return entity;
        }
    }

    public List<TeacherDegreeDTO> toDto(List<TeacherDegreeModel> all) {
        if (all == null) {
            return null;
        }
        return all.stream().map(this::toDto).toList();
    }

    public List<TeacherDegreeModel> toEntity(List<TeacherDegreeDTO> all) {
        if (all == null) {
            return null;
        }
        return all.stream().map(this::toEntity).toList();
    }
}
