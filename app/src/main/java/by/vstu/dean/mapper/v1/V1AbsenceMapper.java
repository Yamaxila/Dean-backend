package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.V1AbsenceDTO;
import by.vstu.dean.models.lessons.AbsenceModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1AbsenceMapper extends BaseMapperInterface<V1AbsenceDTO, AbsenceModel> {

    @Override
    default V1AbsenceDTO toDto(AbsenceModel entity) {
        return entity == null ? null : (V1AbsenceDTO) ReflectionUtils.mapObject(entity, new V1AbsenceDTO(), false, false);
    }

}
