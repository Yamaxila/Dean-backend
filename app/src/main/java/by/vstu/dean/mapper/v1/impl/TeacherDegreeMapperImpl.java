package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.TeacherDegreeDTO;
import by.vstu.dean.mapper.v1.TeacherDegreeMapper;
import by.vstu.dean.models.lessons.TeacherDegreeModel;
import by.vstu.dean.repo.TeacherDegreeModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TeacherDegreeMapperImpl implements TeacherDegreeMapper {

    @Autowired
    private TeacherDegreeModelRepository teacherDegreeModelRepository;

    public TeacherDegreeModel toEntity(TeacherDegreeDTO dto) {
        if (dto == null) {
            return null;
        } else {

            Optional<TeacherDegreeModel> optionalTeacherDegreeModel = this.teacherDegreeModelRepository.findById(dto.getId());
            TeacherDegreeModel teacherDegreeModel = new TeacherDegreeModel();

            if (optionalTeacherDegreeModel.isPresent())
                teacherDegreeModel = optionalTeacherDegreeModel.get();

            return (TeacherDegreeModel) ReflectionUtils.mapObject(teacherDegreeModel, dto, true, optionalTeacherDegreeModel.isPresent());
        }
    }
}
