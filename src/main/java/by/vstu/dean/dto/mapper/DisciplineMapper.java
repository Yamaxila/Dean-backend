package by.vstu.dean.dto.mapper;

import by.vstu.dean.dto.future.BaseMapperInterface;
import by.vstu.dean.dto.future.lessons.DisciplineDTO;
import by.vstu.dean.future.models.lessons.DisciplineModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DisciplineMapper extends BaseMapperInterface<DisciplineDTO, DisciplineModel> {
}
