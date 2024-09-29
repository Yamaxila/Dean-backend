package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.specs.V1QualificationDTO;
import by.vstu.dean.models.specs.QualificationModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1QualificationMapper extends BaseMapperInterface<V1QualificationDTO, QualificationModel> {

    @Override
    default V1QualificationDTO toDto(QualificationModel entity) {
        return entity == null ? null : (V1QualificationDTO) ReflectionUtils.mapObject(entity, new V1QualificationDTO(), false, false);
    }

}
