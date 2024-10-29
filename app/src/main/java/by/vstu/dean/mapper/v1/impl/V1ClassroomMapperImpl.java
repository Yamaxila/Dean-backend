package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.rooms.V1ClassroomDTO;
import by.vstu.dean.mapper.v1.V1ClassroomMapper;
import by.vstu.dean.models.rooms.ClassroomModel;
import by.vstu.dean.services.ClassroomService;
import by.vstu.dean.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class V1ClassroomMapperImpl implements V1ClassroomMapper {

    @Autowired
    private ClassroomService classroomService;
    @Autowired
    private DepartmentService departmentService;

    @Override
    public ClassroomModel toEntity(V1ClassroomDTO dto) {
        if (dto == null) {
            return null;
        }

        ClassroomModel classroomModel = new ClassroomModel();

        if(dto.getId() != null)
            classroomModel = this.classroomService.getById(dto.getId()).orElse(new ClassroomModel());

        if (dto.getDepartmentId() != null)
            classroomModel.setDepartment(this.departmentService.getById(dto.getDepartmentId()).orElseThrow());

        return (ClassroomModel) ReflectionUtils.mapObject(new ClassroomModel(), dto, true, dto.getId() != null);
    }

    @Override
    public ClassroomModel partialUpdate(V1ClassroomDTO dto, ClassroomModel entity) {
        if(dto == null)
            return null;

        ClassroomModel classroomModel = V1ClassroomMapper.super.partialUpdate(dto, entity);

        if(dto.getDepartmentId() != null)
            classroomModel.setDepartment(this.departmentService.getById(dto.getDepartmentId()).orElseThrow());

        return classroomModel;
    }
}