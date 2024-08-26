package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.DisciplineDTO;
import by.vstu.dean.mapper.v1.DepartmentMapper;
import by.vstu.dean.mapper.v1.DisciplineMapper;
import by.vstu.dean.models.lessons.DisciplineModel;
import by.vstu.dean.services.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class DisciplineMapperImpl implements DisciplineMapper {

    @Autowired
    private DisciplineService disciplineService;
    @Autowired
    private DepartmentMapper departmentMapper;

    public DisciplineModel toEntity(DisciplineDTO dto) {
        if (dto == null) {
            return null;
        }

        DisciplineModel disciplineModel = new DisciplineModel();

        if(dto.getId() != null)
            disciplineModel = this.disciplineService.getById(dto.getId()).orElse(new DisciplineModel());

        disciplineModel = (DisciplineModel) ReflectionUtils.mapObject(disciplineModel, dto, true, dto.getId() != null);

        if(dto.getDepartment() != null)
            disciplineModel.setDepartment(this.departmentMapper.toEntity(dto.getDepartment()));

        return disciplineModel;

    }

    public DisciplineDTO toDto(DisciplineModel entity) {
        if(entity == null)
            return null;

        DisciplineDTO disciplineDTO = (DisciplineDTO) ReflectionUtils.mapObject(entity, new DisciplineDTO(), false, false);
        disciplineDTO.setDepartment(this.departmentMapper.toDto(entity.getDepartment()));
        return disciplineDTO;

    }

    public DisciplineModel partialUpdate(DisciplineDTO dto, DisciplineModel entity) {
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
