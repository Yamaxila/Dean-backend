package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.V1TeacherDegreeDTO;
import by.vstu.dean.mapper.v1.V1TeacherDegreeMapper;
import by.vstu.dean.models.lessons.TeacherDegreeModel;
import by.vstu.dean.services.TeacherDegreeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class V1TeacherDegreeMapperImpl implements V1TeacherDegreeMapper {

    @Autowired
    private TeacherDegreeService teacherDegreeService;

    public TeacherDegreeModel toEntity(V1TeacherDegreeDTO dto) {
        if (dto == null) {
            return null;
        }

        TeacherDegreeModel teacherDegreeModel = new TeacherDegreeModel();

        if (dto.getId() != null)
            teacherDegreeModel = this.teacherDegreeService.getById(dto.getId()).orElse(new TeacherDegreeModel());

        return (TeacherDegreeModel) ReflectionUtils.mapObject(teacherDegreeModel, dto, true, dto.getId() != null);
    }
}
