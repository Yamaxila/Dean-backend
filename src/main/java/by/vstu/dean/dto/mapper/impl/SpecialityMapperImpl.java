package by.vstu.dean.dto.mapper.impl;

import by.vstu.dean.dto.BaseMapperImpl;
import by.vstu.dean.dto.future.specs.SpecialityDTO;
import by.vstu.dean.dto.mapper.SpecialityMapper;
import by.vstu.dean.future.models.specs.SpecialityModel;
import by.vstu.dean.future.repo.SpecialityModelRepository;
import by.vstu.dean.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component
public class SpecialityMapperImpl extends BaseMapperImpl implements SpecialityMapper {

    @Autowired
    private SpecialityModelRepository specialityService;
    @Autowired
    private DepartmentService departmentService;

    @Override
    public SpecialityModel toEntity(SpecialityDTO dto) {
        if (dto == null) {
            return null;
        }

        Optional<SpecialityModel> oSpecialityModel = this.specialityService.findById(dto.getId());

        if(oSpecialityModel.isPresent())
            return oSpecialityModel.get();

        SpecialityModel specialityModel = new SpecialityModel();

        specialityModel.setId(dto.getId());
        specialityModel.setSourceId(dto.getSourceId());
        specialityModel.setStatus(dto.getStatus());
        specialityModel.setUpdated(dto.getUpdated());
        specialityModel.setName(dto.getName());
        specialityModel.setShortName(dto.getShortName());
        specialityModel.setSpecCode(dto.getSpecCode());
        specialityModel.setDepartment(this.departmentService.getById(dto.getDepartmentId()).orElseThrow());

        return specialityModel;

    }

    @Override
    public SpecialityDTO toDto(SpecialityModel entity) {
        if (entity == null) {
            return null;
        }

        SpecialityDTO specialityDTO = new SpecialityDTO();

        specialityDTO.setId(entity.getId());
        specialityDTO.setSourceId(entity.getSourceId());
        specialityDTO.setUpdated(entity.getUpdated());
        specialityDTO.setStatus(entity.getStatus());
        specialityDTO.setName(entity.getName());
        specialityDTO.setShortName(entity.getShortName());
        specialityDTO.setSpecCode(entity.getSpecCode());
        specialityDTO.setDepartmentId(entity.getDepartment() != null ? entity.getDepartment().getId() : 0);

        return specialityDTO;

    }

    @Override
    public SpecialityModel partialUpdate(SpecialityDTO dto, SpecialityModel entity) {
        if (dto == null) {
            return null;
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
        if (dto.getShortName() != null) {
            entity.setShortName(dto.getShortName());
        }
        if (dto.getSpecCode() != null) {
            entity.setSpecCode(dto.getSpecCode());
        }

        if (dto.getDepartmentId() != null) {
            entity.setDepartment(this.departmentService.getById(dto.getDepartmentId()).orElseThrow());
        }

        return entity;
    }

    @Override
    public List<SpecialityDTO> toDto(List<SpecialityModel> all) {
        return all.stream().map(this::toDto).toList();
    }

    @Override
    public List<SpecialityModel> toEntity(List<SpecialityDTO> all) {
        return all.stream().map(this::toEntity).toList();
    }
}
