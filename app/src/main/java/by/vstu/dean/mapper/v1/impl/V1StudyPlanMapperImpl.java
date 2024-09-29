package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.dto.v1.lessons.V1StudyPlanDTO;
import by.vstu.dean.mapper.v1.V1StudyPlanMapper;
import by.vstu.dean.models.lessons.StudyPlanModel;
import org.springframework.stereotype.Component;

@Component
public class V1StudyPlanMapperImpl implements V1StudyPlanMapper {

    public StudyPlanModel toEntity(V1StudyPlanDTO dto) {
       throw new RuntimeException("Not implemented!");
    }

    public V1StudyPlanDTO toDto(StudyPlanModel entity) {
        throw new RuntimeException("Not implemented!");
    }

    public StudyPlanModel partialUpdate(V1StudyPlanDTO dto, StudyPlanModel entity) {
        throw new RuntimeException("Not implemented!");
    }
}
