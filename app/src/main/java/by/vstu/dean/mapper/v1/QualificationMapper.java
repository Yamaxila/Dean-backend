package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.specs.QualificationDTO;
import by.vstu.dean.models.specs.QualificationModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface QualificationMapper extends BaseMapperInterface<QualificationDTO, QualificationModel> {

    @Override
    default QualificationDTO toDto(QualificationModel entity) {
        return entity == null ? null : (QualificationDTO) ReflectionUtils.mapObject(entity, new QualificationDTO(), false, false);
    }

}
