package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.FacultyDTO;
import by.vstu.dean.models.FacultyModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

@Component
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface FacultyMapper extends BaseMapperInterface<FacultyDTO, FacultyModel> {

    @Override
    default FacultyDTO toDto(FacultyModel entity) {
        return entity == null ? null : (FacultyDTO) ReflectionUtils.mapObject(entity, new FacultyDTO(), false, false);
    }
}
