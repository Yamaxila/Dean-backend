package by.vstu.dean.dto.mapper;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.dto.v1.lessons.AbsenceDTO;
import by.vstu.dean.models.lessons.AbsenceModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AbsenceMapper extends BaseMapperInterface<AbsenceDTO, AbsenceModel> {
}
