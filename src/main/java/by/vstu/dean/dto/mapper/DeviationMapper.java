package by.vstu.dean.dto.mapper;


import by.vstu.dean.dto.future.BaseMapperInterface;
import by.vstu.dean.dto.future.students.DeviationDTO;
import by.vstu.dean.future.models.students.DeviationModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface DeviationMapper extends BaseMapperInterface<DeviationDTO, DeviationModel> {
}
