package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.TeacherDegreeDTO;
import by.vstu.dean.models.lessons.TeacherDegreeModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TeacherDegreeMapper extends BaseMapperInterface<TeacherDegreeDTO, TeacherDegreeModel> {

    @Override
    default TeacherDegreeDTO toDto(TeacherDegreeModel entity) {
        return entity == null ? null : (TeacherDegreeDTO) ReflectionUtils.mapObject(entity, new TeacherDegreeDTO(), false, false);
    }

}