package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.specs.SpecialityDTO;
import by.vstu.dean.mapper.v1.SpecialityMapper;
import by.vstu.dean.models.specs.SpecialityModel;
import by.vstu.dean.services.DepartmentService;
import by.vstu.dean.services.SpecialityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class SpecialityMapperImpl implements SpecialityMapper {

    @Autowired
    private SpecialityService specialityService;
    @Autowired
    private DepartmentService departmentService;

    @Override
    public SpecialityModel toEntity(SpecialityDTO dto) {
        if (dto == null) {
            return null;
        }

        Optional<SpecialityModel> oSpecialityModel = this.specialityService.getById(dto.getId());
        SpecialityModel specialityModel = new SpecialityModel();

        if (oSpecialityModel.isPresent())
            specialityModel = oSpecialityModel.get();

        specialityModel = (SpecialityModel) ReflectionUtils.mapObject(specialityModel, dto, true, oSpecialityModel.isPresent());

        specialityModel.setDepartment(this.departmentService.getById(dto.getDepartmentId()).orElseThrow());

        return specialityModel;

    }

    @Override
    public SpecialityModel partialUpdate(SpecialityDTO dto, SpecialityModel entity) {
        if (dto == null) {
            return null;
        }

        entity = SpecialityMapper.super.partialUpdate(dto, entity);

        if (dto.getDepartmentId() != null) {
            entity.setDepartment(this.departmentService.getById(dto.getDepartmentId()).orElseThrow());
        }

        return entity;
    }
}
