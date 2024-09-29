package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.V1DisciplineDTO;
import by.vstu.dean.models.lessons.DisciplineModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1DisciplineMapper extends BaseMapperInterface<V1DisciplineDTO, DisciplineModel> {

    @Override
    default V1DisciplineDTO toDto(DisciplineModel entity) {
        return entity == null ? null : (V1DisciplineDTO) ReflectionUtils.mapObject(entity, new V1DisciplineDTO(), false, false);
    }

}
