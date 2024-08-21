package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.DisciplineDTO;
import by.vstu.dean.models.lessons.DisciplineModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DisciplineMapper extends BaseMapperInterface<DisciplineDTO, DisciplineModel> {

    @Override
    default DisciplineDTO toDto(DisciplineModel entity) {
        return entity == null ? null : (DisciplineDTO) ReflectionUtils.mapObject(entity, new DisciplineDTO(), false, false);
    }

}
