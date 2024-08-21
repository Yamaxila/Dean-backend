package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.CitizenshipDTO;
import by.vstu.dean.models.students.CitizenshipModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface CitizenshipMapper extends BaseMapperInterface<CitizenshipDTO, CitizenshipModel> {
    @Override
    default CitizenshipDTO toDto(CitizenshipModel entity) {
        return entity == null ? null : (CitizenshipDTO) ReflectionUtils.mapObject(entity, new CitizenshipDTO(), false, false);
    }
}