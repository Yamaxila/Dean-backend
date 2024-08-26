package by.vstu.dean.mapper.v1;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.MessageDTO;
import by.vstu.dean.models.internal.MessageModel;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MessageMapper extends BaseMapperInterface<MessageDTO, MessageModel> {

    @Override
    default MessageDTO toDto(MessageModel entity) {
        return entity == null ? null : (MessageDTO) ReflectionUtils.mapObject(entity, new MessageDTO(), false, false);
    }

}