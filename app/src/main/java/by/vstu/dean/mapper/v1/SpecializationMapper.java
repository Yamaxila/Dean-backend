package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.specs.SpecializationDTO;
import by.vstu.dean.models.specs.SpecializationModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SpecializationMapper extends BaseMapperInterface<SpecializationDTO, SpecializationModel> {

    @Override
    default SpecializationDTO toDto(SpecializationModel entity) {
        return entity == null ? null : (SpecializationDTO) ReflectionUtils.mapObject(entity, new SpecializationDTO(), false, false);
    }

}