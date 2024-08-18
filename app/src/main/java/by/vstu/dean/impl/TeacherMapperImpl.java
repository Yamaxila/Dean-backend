package by.vstu.dean.impl;

import by.vstu.dean.dto.v1.lessons.TeacherDTO;
import by.vstu.dean.dto.mapper.TeacherDegreeMapper;
import by.vstu.dean.dto.mapper.TeacherMapper;
import by.vstu.dean.models.lessons.TeacherModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class TeacherMapperImpl implements TeacherMapper {

    //    @Autowired
//    private TeacherService teacherService;
    @Autowired
    private TeacherDegreeMapper teacherDegreeMapper;

    public TeacherModel toEntity(TeacherDTO dto) {
        if (dto == null) {
            return null;
        } else {
            TeacherModel teacherModel = new TeacherModel();
            teacherModel.setId(dto.getId());
            teacherModel.setSourceId(dto.getSourceId());
            teacherModel.setStatus(dto.getStatus());
            teacherModel.setUpdated(LocalDateTime.now());
            teacherModel.setSurname(dto.getSurname());
            teacherModel.setName(dto.getName());
            teacherModel.setPatronymic(dto.getPatronymic());
            teacherModel.setDegree(this.teacherDegreeMapper.toEntity(dto.getDegree()));
            return teacherModel;
        }
    }

    public TeacherDTO toDto(TeacherModel entity) {
        if (entity == null) {
            return null;
        } else {
            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setId(entity.getId());
            teacherDTO.setSourceId(entity.getSourceId());
            teacherDTO.setUpdated(entity.getUpdated());
            teacherDTO.setStatus(entity.getStatus());
            teacherDTO.setSurname(entity.getSurname());
            teacherDTO.setName(entity.getName());
            teacherDTO.setPatronymic(entity.getPatronymic());
            teacherDTO.setDegree(this.teacherDegreeMapper.toDto(entity.getDegree()));
            return teacherDTO;
        }
    }

    public TeacherModel partialUpdate(TeacherDTO dto, TeacherModel entity) {
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

            if (dto.getSurname() != null) {
                entity.setSurname(dto.getSurname());
            }

            if (dto.getName() != null) {
                entity.setName(dto.getName());
            }

            if (dto.getPatronymic() != null) {
                entity.setPatronymic(dto.getPatronymic());
            }

            if (dto.getDegree() != null) {
                if (entity.getDegree() == null) {
                    entity.setDegree(null);
                }

                entity.setDegree(this.teacherDegreeMapper.partialUpdate(dto.getDegree(), entity.getDegree()));
            }

            return entity;
        }
    }

    public List<TeacherDTO> toDto(List<TeacherModel> all) {
        if (all == null) {
            return null;
        }
        return all.stream().map(this::toDto).toList();
    }

    public List<TeacherModel> toEntity(List<TeacherDTO> all) {
        if (all == null) {
            return null;
        }
        return all.stream().map(this::toEntity).toList();
    }

}
