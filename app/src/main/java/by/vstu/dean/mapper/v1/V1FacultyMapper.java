package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.V1FacultyDTO;
import by.vstu.dean.models.FacultyModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1FacultyMapper extends BaseMapperInterface<V1FacultyDTO, FacultyModel> {

    @Override
    default V1FacultyDTO toDto(FacultyModel entity) {
        return entity == null ? null : (V1FacultyDTO) ReflectionUtils.mapObject(entity, new V1FacultyDTO(), false, false);
    }
}
