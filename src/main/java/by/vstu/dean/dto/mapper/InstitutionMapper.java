package by.vstu.dean.dto.mapper;

import by.vstu.dean.dto.future.BaseMapperInterface;
import by.vstu.dean.dto.future.students.InstitutionDTO;
import by.vstu.dean.future.models.students.InstitutionModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface InstitutionMapper extends BaseMapperInterface<InstitutionDTO, InstitutionModel> {
}
