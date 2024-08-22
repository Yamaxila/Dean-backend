package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.StudentLanguageDTO;
import by.vstu.dean.mapper.v1.StudentLanguageMapper;
import by.vstu.dean.models.students.StudentLanguageModel;
import by.vstu.dean.repo.StudentLanguageModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class StudentLanguageMapperImpl implements StudentLanguageMapper {

    @Autowired
    private StudentLanguageModelRepository studentLanguageService;

    public StudentLanguageModel toEntity(StudentLanguageDTO dto) {
        if (dto == null) {
            return null;
        }
        Optional<StudentLanguageModel> optionalStudentLanguageModel = this.studentLanguageService.findById(dto.getId());
        StudentLanguageModel studentLanguageModel = new StudentLanguageModel();
        if (optionalStudentLanguageModel.isPresent())
            studentLanguageModel = optionalStudentLanguageModel.get();

        return (StudentLanguageModel) ReflectionUtils.mapObject(studentLanguageModel, dto, true, false);
    }
}
