package by.vstu.dean.dto.mapper;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.dto.v1.specs.QualificationDTO;
import by.vstu.dean.models.specs.QualificationModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface QualificationMapper extends BaseMapperInterface<QualificationDTO, QualificationModel> {
}
