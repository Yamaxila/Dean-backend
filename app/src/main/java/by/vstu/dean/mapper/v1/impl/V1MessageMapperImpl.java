package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.V1MessageDTO;
import by.vstu.dean.mapper.v1.V1MessageMapper;
import by.vstu.dean.models.internal.MessageModel;
import by.vstu.dean.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class V1MessageMapperImpl implements V1MessageMapper {

    @Autowired
    private MessageService messageService;

    @Override
    public MessageModel toEntity(V1MessageDTO dto) {

        if(dto == null)
            return null;

        MessageModel model = new MessageModel();

        if(dto.getId() != null)
            model = this.messageService.getById(dto.getId()).orElse(new MessageModel());

        return (MessageModel) ReflectionUtils.mapObject(model, dto, true, dto.getId() != null);
    }
}
