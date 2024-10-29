package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.specs.V1SpecializationDTO;
import by.vstu.dean.models.specs.SpecializationModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1SpecializationMapper extends BaseMapperInterface<V1SpecializationDTO, SpecializationModel> {

    @Override
    default V1SpecializationDTO toDto(SpecializationModel entity) {
        return entity == null ? null : (V1SpecializationDTO) ReflectionUtils.mapObject(entity, new V1SpecializationDTO(), false, false);
    }

}