package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.specs.V1SpecialityDTO;
import by.vstu.dean.models.specs.SpecialityModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1SpecialityMapper extends BaseMapperInterface<V1SpecialityDTO, SpecialityModel> {

    @Override
    default V1SpecialityDTO toDto(SpecialityModel entity) {
        return entity == null ? null : (V1SpecialityDTO) ReflectionUtils.mapObject(entity, new V1SpecialityDTO(), false, false);
    }

}