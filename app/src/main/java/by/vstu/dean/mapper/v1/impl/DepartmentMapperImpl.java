package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.DepartmentDTO;
import by.vstu.dean.mapper.v1.DepartmentMapper;
import by.vstu.dean.mapper.v1.FacultyMapper;
import by.vstu.dean.mapper.v1.TeacherMapper;
import by.vstu.dean.models.lessons.DepartmentModel;
import by.vstu.dean.repo.DepartmentModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class DepartmentMapperImpl implements DepartmentMapper {

    @Autowired
    private DepartmentModelRepository departmentModelRepository;
    @Autowired
    private FacultyMapper facultyMapper;
    @Autowired
    private TeacherMapper teacherMapper;


    @Override
    public DepartmentModel toEntity(DepartmentDTO dto) {
        if (dto == null) {
            return null;
        }

        Optional<DepartmentModel> optionalDepartmentModel = this.departmentModelRepository.findById(dto.getId());
        DepartmentModel departmentModel = new DepartmentModel();

        if (optionalDepartmentModel.isPresent())
            departmentModel = optionalDepartmentModel.get();

        departmentModel = (DepartmentModel) ReflectionUtils.mapObject(departmentModel, dto, true, true);

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
