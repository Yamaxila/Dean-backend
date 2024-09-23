package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.InstitutionDTO;
import by.vstu.dean.models.students.internal.InstitutionModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface InstitutionMapper extends BaseMapperInterface<InstitutionDTO, InstitutionModel> {

    @Override
    default InstitutionDTO toDto(InstitutionModel entity) {
        return entity == null ? null : (InstitutionDTO) ReflectionUtils.mapObject(entity, new InstitutionDTO(), false, false);
    }

}
