package by.vstu.dean.dto.mapper;

import by.vstu.dean.dto.future.BaseMapperInterface;
import by.vstu.dean.dto.future.specs.SpecializationDTO;
import by.vstu.dean.future.models.specs.SpecializationModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface SpecializationMapper extends BaseMapperInterface<SpecializationDTO, SpecializationModel> {
}