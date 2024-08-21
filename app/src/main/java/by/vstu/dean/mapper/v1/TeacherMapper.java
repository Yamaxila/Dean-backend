package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.TeacherDTO;
import by.vstu.dean.models.lessons.TeacherModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface TeacherMapper extends BaseMapperInterface<TeacherDTO, TeacherModel> {

    @Override
    default TeacherDTO toDto(TeacherModel entity) {
        return entity == null ? null : (TeacherDTO) ReflectionUtils.mapObject(entity, new TeacherDTO(), false, false);
    }

}
