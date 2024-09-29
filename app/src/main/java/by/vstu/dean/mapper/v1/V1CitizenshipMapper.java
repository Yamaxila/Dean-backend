package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.V1CitizenshipDTO;
import by.vstu.dean.models.students.internal.CitizenshipModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1CitizenshipMapper extends BaseMapperInterface<V1CitizenshipDTO, CitizenshipModel> {
    @Override
    default V1CitizenshipDTO toDto(CitizenshipModel entity) {
        return entity == null ? null : (V1CitizenshipDTO) ReflectionUtils.mapObject(entity, new V1CitizenshipDTO(), false, false);
    }
}