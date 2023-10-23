package by.vstu.dean.dto.mapper;

import by.vstu.dean.dto.future.BaseMapperInterface;
import by.vstu.dean.dto.future.specs.QualificationDTO;
import by.vstu.dean.future.models.specs.QualificationModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface QualificationMapper extends BaseMapperInterface<QualificationDTO, QualificationModel> {
}
