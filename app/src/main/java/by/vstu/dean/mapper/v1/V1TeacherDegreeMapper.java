package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.V1TeacherDegreeDTO;
import by.vstu.dean.models.lessons.TeacherDegreeModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1TeacherDegreeMapper extends BaseMapperInterface<V1TeacherDegreeDTO, TeacherDegreeModel> {

    @Override
    default V1TeacherDegreeDTO toDto(TeacherDegreeModel entity) {
        return entity == null ? null : (V1TeacherDegreeDTO) ReflectionUtils.mapObject(entity, new V1TeacherDegreeDTO(), false, false);
    }

}