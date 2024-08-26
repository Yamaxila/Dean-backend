package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.TeacherDegreeDTO;
import by.vstu.dean.mapper.v1.TeacherDegreeMapper;
import by.vstu.dean.models.lessons.TeacherDegreeModel;
import by.vstu.dean.services.TeacherDegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TeacherDegreeMapperImpl implements TeacherDegreeMapper {

    @Autowired
    private TeacherDegreeService teacherDegreeService;

    public TeacherDegreeModel toEntity(TeacherDegreeDTO dto) {
        if (dto == null) {
            return null;
        }

        TeacherDegreeModel teacherDegreeModel = new TeacherDegreeModel();

        if (dto.getId() != null)
            teacherDegreeModel = this.teacherDegreeService.getById(dto.getId()).orElse(new TeacherDegreeModel());

        return (TeacherDegreeModel) ReflectionUtils.mapObject(teacherDegreeModel, dto, true, dto.getId() != null);
    }
}
