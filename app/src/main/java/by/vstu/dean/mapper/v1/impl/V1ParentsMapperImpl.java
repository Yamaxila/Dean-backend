package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.dto.v1.students.V1ParentDTO;
import by.vstu.dean.mapper.v1.V1ParentsMapper;
import by.vstu.dean.models.students.internal.ParentModel;
import org.springframework.stereotype.Component;

@Component
public class V1ParentsMapperImpl implements V1ParentsMapper {

    @Override
    public ParentModel toEntity(V1ParentDTO dto) {
        throw new RuntimeException("Not supported yet.");
    }
}
