package by.vstu.dean.dto.mapper;


import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.dto.v1.students.DeviationDTO;
import by.vstu.dean.models.students.DeviationModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DeviationMapper extends BaseMapperInterface<DeviationDTO, DeviationModel> {
}
