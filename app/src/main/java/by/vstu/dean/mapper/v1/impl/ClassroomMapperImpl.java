package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.rooms.ClassroomDTO;
import by.vstu.dean.mapper.v1.ClassroomMapper;
import by.vstu.dean.models.rooms.ClassroomModel;
import by.vstu.dean.repo.ClassroomModelRepository;
import by.vstu.dean.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ClassroomMapperImpl implements ClassroomMapper {

    @Autowired
    private ClassroomModelRepository classroomModelRepo;
    @Autowired
    private DepartmentService departmentService;

    @Override
    public ClassroomModel toEntity(ClassroomDTO dto) {
        if (dto == null) {
            return null;
        }

        Optional<ClassroomModel> optionalClassroomModel = this.classroomModelRepo.findById(dto.getId());

        ClassroomModel classroomModel = new ClassroomModel();

        if(optionalClassroomModel.isPresent())
            classroomModel = optionalClassroomModel.get();

        if (dto.getDepartmentId() != null)
            classroomModel.setDepartment(this.departmentService.getById(dto.getDepartmentId()).orElseThrow());

        return (ClassroomModel) ReflectionUtils.mapObject(new ClassroomModel(), dto, true, optionalClassroomModel.isPresent());
    }

    @Override
    public ClassroomModel partialUpdate(ClassroomDTO dto, ClassroomModel entity) {
        if(dto == null)
            return null;

        ClassroomModel classroomModel = ClassroomMapper.super.partialUpdate(dto, entity);

        if(dto.getDepartmentId() != null)
            classroomModel.setDepartment(this.departmentService.getById(dto.getDepartmentId()).orElseThrow());

        return classroomModel;
    }
}