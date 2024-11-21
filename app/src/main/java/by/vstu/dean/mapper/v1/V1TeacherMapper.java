package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.V1TeacherDTO;
import by.vstu.dean.models.lessons.TeacherModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1TeacherMapper extends BaseMapperInterface<V1TeacherDTO, TeacherModel> {

    @Override
    default V1TeacherDTO toDto(TeacherModel entity) {
        return entity == null ? null : (V1TeacherDTO) ReflectionUtils.mapObject(entity, new V1TeacherDTO(), false, false);
    }

}
