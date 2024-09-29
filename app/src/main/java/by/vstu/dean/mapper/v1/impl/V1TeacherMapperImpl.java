package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.V1TeacherDTO;
import by.vstu.dean.mapper.v1.V1TeacherDegreeMapper;
import by.vstu.dean.mapper.v1.V1TeacherMapper;
import by.vstu.dean.models.lessons.TeacherModel;
import by.vstu.dean.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class V1TeacherMapperImpl implements V1TeacherMapper {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private V1TeacherDegreeMapper teacherDegreeMapper;

    public TeacherModel toEntity(V1TeacherDTO dto) {
        if (dto == null) {
            return null;
        }

        TeacherModel teacherModel = new TeacherModel();

        if(dto.getId() != null)
            teacherModel = this.teacherService.getById(dto.getId()).orElse(new TeacherModel());

        teacherModel = (TeacherModel) ReflectionUtils.mapObject(teacherModel, dto, true, dto.getId() != null);

        teacherModel.setDegree(this.teacherDegreeMapper.toEntity(dto.getDegree()));
        return teacherModel;
    }

    public V1TeacherDTO toDto(TeacherModel entity) {
        if (entity == null) {
            return null;
        }
        V1TeacherDTO teacherDTO = V1TeacherMapper.super.toDto(entity);
        teacherDTO.setDegree(this.teacherDegreeMapper.toDto(entity.getDegree()));
        return teacherDTO;
    }

    public TeacherModel partialUpdate(V1TeacherDTO dto, TeacherModel entity) {
        if (dto == null) {
            return null;
        }

        entity = V1TeacherMapper.super.partialUpdate(dto, entity);

        if (dto.getDegree() != null) {
            entity.setDegree(this.teacherDegreeMapper.partialUpdate(dto.getDegree(), entity.getDegree()));
        }

        return entity;
    }
}
