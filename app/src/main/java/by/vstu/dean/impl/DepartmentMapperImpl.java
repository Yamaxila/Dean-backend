package by.vstu.dean.impl;

import by.vstu.dean.dto.v1.lessons.DepartmentDTO;
import by.vstu.dean.dto.mapper.DepartmentMapper;
import by.vstu.dean.dto.mapper.FacultyMapper;
import by.vstu.dean.dto.mapper.TeacherMapper;
import by.vstu.dean.models.FacultyModel;
import by.vstu.dean.models.lessons.DepartmentModel;
import by.vstu.dean.repo.DepartmentModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
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

        if (optionalDepartmentModel.isPresent())
            return optionalDepartmentModel.get();

        DepartmentModel departmentModel = new DepartmentModel();

        departmentModel.setId(dto.getId());
        departmentModel.setSourceId(dto.getSourceId());
        departmentModel.setStatus(dto.getStatus());
        departmentModel.setUpdated(dto.getUpdated());
        departmentModel.setName(dto.getName());
        departmentModel.setShortName(dto.getShortName());
        departmentModel.setFaculty(this.facultyMapper.toEntity(dto.getFaculty()));
//        departmentModel.setTeachers(new HashSet<>(this.teacherMapper.toEntity(dto.getTeachers().stream().toList())));

        return departmentModel;
    }

    @Override
    public DepartmentDTO toDto(DepartmentModel entity) {
        if (entity == null) {
            return null;
        }

        DepartmentDTO departmentDTO = new DepartmentDTO();

        departmentDTO.setId(entity.getId());
        departmentDTO.setSourceId(entity.getSourceId());
        departmentDTO.setUpdated(entity.getUpdated());
        departmentDTO.setStatus(entity.getStatus());
        departmentDTO.setName(entity.getName());
        departmentDTO.setShortName(entity.getShortName());
        departmentDTO.setFaculty(this.facultyMapper.toDto(entity.getFaculty()));
//        departmentDTO.setTeachers(new HashSet<>(this.teacherMapper.toDto(entity.getTeachers().stream().toList())));

        return departmentDTO;
    }

    @Override
    public DepartmentModel partialUpdate(DepartmentDTO dto, DepartmentModel entity) {
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
            entity.setUpdated(dto.getUpdated());
        }
        if (dto.getName() != null) {
            entity.setName(dto.getName());
        }
        if (dto.getShortName() != null) {
            entity.setShortName(dto.getShortName());
        }
        if (dto.getFaculty() != null) {
            if (entity.getFaculty() == null) {
                entity.setFaculty(new FacultyModel());
            }
            this.facultyMapper.partialUpdate(dto.getFaculty(), entity.getFaculty());
        }

        return entity;
    }

    @Override
    public List<DepartmentDTO> toDto(List<DepartmentModel> all) {
        return all.stream().map(this::toDto).toList();
    }

    @Override
    public List<DepartmentModel> toEntity(List<DepartmentDTO> all) {
        return all.stream().map(this::toEntity).toList();
    }
}
