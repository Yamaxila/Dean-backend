package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.AbsenceDTO;
import by.vstu.dean.models.lessons.AbsenceModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AbsenceMapper extends BaseMapperInterface<AbsenceDTO, AbsenceModel> {

    @Override
    default AbsenceDTO toDto(AbsenceModel entity) {
        return entity == null ? null : (AbsenceDTO) ReflectionUtils.mapObject(entity, new AbsenceDTO(), false, false);
    }

}
