package by.vstu.dean.dto.mapper;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.dto.v1.lessons.DisciplineDTO;
import by.vstu.dean.models.lessons.DisciplineModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DisciplineMapper extends BaseMapperInterface<DisciplineDTO, DisciplineModel> {
}
