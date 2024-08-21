package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.dto.v1.lessons.StudyPlanDTO;
import by.vstu.dean.mapper.v1.StudyPlanMapper;
import by.vstu.dean.models.lessons.StudyPlanModel;
import org.springframework.stereotype.Component;

@Component
public class StudyPlanMapperImpl implements StudyPlanMapper {

    public StudyPlanModel toEntity(StudyPlanDTO dto) {
       throw new RuntimeException("Not implemented!");
    }

    public StudyPlanDTO toDto(StudyPlanModel entity) {
        throw new RuntimeException("Not implemented!");
    }

    public StudyPlanModel partialUpdate(StudyPlanDTO dto, StudyPlanModel entity) {
        throw new RuntimeException("Not implemented!");
    }
}
