package by.vstu.dean.mapper.v1.pub;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.pub.teachers.V1PublicTeacherDTO;
import by.vstu.dean.models.lessons.TeacherModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1PublicTeacherMapper extends BaseMapperInterface<V1PublicTeacherDTO, TeacherModel> {

    @Override
    default V1PublicTeacherDTO toDto(TeacherModel entity) {
        return entity == null ? null : (V1PublicTeacherDTO) ReflectionUtils.mapObject(entity, new V1PublicTeacherDTO(), false, false);
    }
}
