package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.V1InstitutionDTO;
import by.vstu.dean.models.students.internal.InstitutionModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1InstitutionMapper extends BaseMapperInterface<V1InstitutionDTO, InstitutionModel> {

    @Override
    default V1InstitutionDTO toDto(InstitutionModel entity) {
        return entity == null ? null : (V1InstitutionDTO) ReflectionUtils.mapObject(entity, new V1InstitutionDTO(), false, false);
    }

}
