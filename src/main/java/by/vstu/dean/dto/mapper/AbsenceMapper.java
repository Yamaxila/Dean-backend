package by.vstu.dean.dto.mapper;

import by.vstu.dean.dto.future.BaseMapperInterface;
import by.vstu.dean.dto.future.lessons.AbsenceDTO;
import by.vstu.dean.future.models.lessons.AbsenceModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface AbsenceMapper extends BaseMapperInterface<AbsenceDTO, AbsenceModel> {
}
