package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.V1ExamTypeDTO;
import by.vstu.dean.mapper.v1.V1ExamTypeMapper;
import by.vstu.dean.models.lessons.ExamModel;
import by.vstu.dean.services.ExamTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class V1ExamTypeMapperImpl implements V1ExamTypeMapper {

    @Autowired
    private ExamTypeService examTypeService;

    public ExamModel toEntity(V1ExamTypeDTO dto) {
        if (dto == null) {
            return null;
        }

        ExamModel examModel = new ExamModel();

        if (dto.getId() != null)
            examModel = this.examTypeService.getById(dto.getId()).orElse(new ExamModel());

        return (ExamModel) ReflectionUtils.mapObject(examModel, dto, true, dto.getId() != null);
    }

}
