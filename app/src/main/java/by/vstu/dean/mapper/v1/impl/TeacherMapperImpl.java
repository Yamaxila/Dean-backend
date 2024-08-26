package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.TeacherDTO;
import by.vstu.dean.mapper.v1.TeacherDegreeMapper;
import by.vstu.dean.mapper.v1.TeacherMapper;
import by.vstu.dean.models.lessons.TeacherModel;
import by.vstu.dean.services.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeacherMapperImpl implements TeacherMapper {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherDegreeMapper teacherDegreeMapper;

    public TeacherModel toEntity(TeacherDTO dto) {
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

    public TeacherDTO toDto(TeacherModel entity) {
        if (entity == null) {
            return null;
        }
        TeacherDTO teacherDTO = TeacherMapper.super.toDto(entity);
        teacherDTO.setDegree(this.teacherDegreeMapper.toDto(entity.getDegree()));
        return teacherDTO;
    }

    public TeacherModel partialUpdate(TeacherDTO dto, TeacherModel entity) {
        if (dto == null) {
            return null;
        }

        entity = TeacherMapper.super.partialUpdate(dto, entity);

        if (dto.getDegree() != null) {
            entity.setDegree(this.teacherDegreeMapper.partialUpdate(dto.getDegree(), entity.getDegree()));
        }

        return entity;
    }
}
