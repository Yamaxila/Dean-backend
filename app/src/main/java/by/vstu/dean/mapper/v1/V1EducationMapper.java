package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.V1EducationDTO;
import by.vstu.dean.models.students.internal.EducationModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1EducationMapper extends BaseMapperInterface<V1EducationDTO, EducationModel> {

    @Override
    default V1EducationDTO toDto(EducationModel entity) {
        return entity == null ? null : (V1EducationDTO) ReflectionUtils.mapObject(entity, new V1EducationDTO(), false, false);
    }

}
