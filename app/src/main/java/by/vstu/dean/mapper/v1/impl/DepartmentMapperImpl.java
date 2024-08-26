package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.DepartmentDTO;
import by.vstu.dean.mapper.v1.DepartmentMapper;
import by.vstu.dean.mapper.v1.FacultyMapper;
import by.vstu.dean.models.lessons.DepartmentModel;
import by.vstu.dean.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private FacultyMapper facultyMapper;

    @Override
    public DepartmentModel toEntity(DepartmentDTO dto) {
        if (dto == null) {
            return null;
        }

        DepartmentModel departmentModel = new DepartmentModel();

        if (dto.getId() != null)
            departmentModel = this.departmentService.getById(dto.getId()).orElse(new DepartmentModel());

        departmentModel = (DepartmentModel) ReflectionUtils.mapObject(departmentModel, dto, true, dto.getId() != null);

        departmentModel.setFaculty(this.facultyMapper.toEntity(dto.getFaculty()));

        return departmentModel;
    }

    @Override
    public DepartmentDTO toDto(DepartmentModel entity) {
        if(entity == null)
            return null;

        DepartmentDTO departmentDTO = DepartmentMapper.super.toDto(entity);
        departmentDTO.setFaculty(this.facultyMapper.toDto(entity.getFaculty()));
        return departmentDTO;
    }

    @Override
    public DepartmentModel partialUpdate(DepartmentDTO dto, DepartmentModel entity) {
        if(dto == null)
            return null;
        entity = DepartmentMapper.super.partialUpdate(dto, entity);

        entity.setFaculty(this.facultyMapper.partialUpdate(dto.getFaculty(), entity.getFaculty()));

        return entity;
    }
}
