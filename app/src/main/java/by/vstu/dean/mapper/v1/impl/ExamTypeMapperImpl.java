package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.ExamTypeDTO;
import by.vstu.dean.mapper.v1.ExamTypeMapper;
import by.vstu.dean.models.lessons.ExamModel;
import by.vstu.dean.repo.ExamModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ExamTypeMapperImpl implements ExamTypeMapper {

    @Autowired
    private ExamModelRepository examModelRepository;

    public ExamModel toEntity(ExamTypeDTO dto) {
        if (dto == null) {
            return null;
        }

        Optional<ExamModel> optionalExamModel = this.examModelRepository.findById(dto.getId());
        ExamModel examModel = new ExamModel();

        if (optionalExamModel.isPresent())
            examModel = optionalExamModel.get();

        return (ExamModel) ReflectionUtils.mapObject(examModel, dto, true, optionalExamModel.isPresent());
    }

}
