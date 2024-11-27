package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.V1ParentDTO;
import by.vstu.dean.models.students.internal.ParentModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1ParentsMapper extends BaseMapperInterface<V1ParentDTO, ParentModel> {
    @Override
    default V1ParentDTO toDto(ParentModel entity) {
        return entity == null ? null : (V1ParentDTO) ReflectionUtils.mapObject(entity, new V1ParentDTO(), false, false);
    }
}
