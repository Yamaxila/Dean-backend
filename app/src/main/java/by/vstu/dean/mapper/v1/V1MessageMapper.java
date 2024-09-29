package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.V1MessageDTO;
import by.vstu.dean.models.internal.MessageModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1MessageMapper extends BaseMapperInterface<V1MessageDTO, MessageModel> {

    @Override
    default V1MessageDTO toDto(MessageModel entity) {
        return entity == null ? null : (V1MessageDTO) ReflectionUtils.mapObject(entity, new V1MessageDTO(), false, false);
    }

}