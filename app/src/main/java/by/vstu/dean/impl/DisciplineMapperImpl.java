package by.vstu.dean.impl;

import by.vstu.dean.dto.mapper.DepartmentMapper;
import by.vstu.dean.dto.mapper.DisciplineMapper;
import by.vstu.dean.dto.v1.lessons.DisciplineDTO;
import by.vstu.dean.models.lessons.DisciplineModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class DisciplineMapperImpl implements DisciplineMapper {

    @Autowired
    private DepartmentMapper departmentMapper;

    public DisciplineModel toEntity(DisciplineDTO dto) {
        if (dto == null) {
            return null;
        }
        DisciplineModel disciplineModel = new DisciplineModel();
        disciplineModel.setId(dto.getId());
        disciplineModel.setSourceId(dto.getSourceId());
        disciplineModel.setStatus(dto.getStatus());
        disciplineModel.setUpdated(LocalDateTime.now());
        disciplineModel.setName(dto.getName());
        disciplineModel.setShortName(dto.getShortName());
        disciplineModel.setDepartment(this.departmentMapper.toEntity(dto.getDepartment()));
        return disciplineModel;

    }

    public DisciplineDTO toDto(DisciplineModel entity) {
        if (entity == null) {
            return null;
        }
        DisciplineDTO disciplineDTO = new DisciplineDTO();
        disciplineDTO.setId(entity.getId());
        disciplineDTO.setSourceId(entity.getSourceId());
        disciplineDTO.setUpdated(entity.getUpdated());
        disciplineDTO.setStatus(entity.getStatus());
        disciplineDTO.setName(entity.getName());
        disciplineDTO.setShortName(entity.getShortName());
        disciplineDTO.setDepartment(this.departmentMapper.toDto(entity.getDepartment()));
        return disciplineDTO;

    }

    public DisciplineModel partialUpdate(DisciplineDTO dto, DisciplineModel entity) {
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

            if (dto.getShortName() != null) {
                entity.setShortName(dto.getShortName());
            }

            if (dto.getDepartment() != null) {
                if (entity.getDepartment() == null) {
                    entity.setDepartment(null);
                } else
                    entity.setDepartment(this.departmentMapper.partialUpdate(dto.getDepartment(), entity.getDepartment()));
            }

            return entity;
        }
    }

    public List<DisciplineDTO> toDto(List<DisciplineModel> all) {
        if (all == null) {
            return null;
        }
        return all.stream().map(this::toDto).toList();
    }

    public List<DisciplineModel> toEntity(List<DisciplineDTO> all) {
        if (all == null) {
            return null;
        }
        return all.stream().map(this::toEntity).toList();
    }
}
