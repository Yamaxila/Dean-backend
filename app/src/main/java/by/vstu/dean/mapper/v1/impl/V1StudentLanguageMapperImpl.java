package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.students.V1StudentLanguageDTO;
import by.vstu.dean.mapper.v1.V1StudentLanguageMapper;
import by.vstu.dean.models.students.internal.StudentLanguageModel;
import by.vstu.dean.services.students.StudentLanguageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class V1StudentLanguageMapperImpl implements V1StudentLanguageMapper {

    @Autowired
    private StudentLanguageService studentLanguageService;

    public StudentLanguageModel toEntity(V1StudentLanguageDTO dto) {
        if (dto == null) {
            return null;
        }
        StudentLanguageModel studentLanguageModel = new StudentLanguageModel();

        if (dto.getId() != null)
            studentLanguageModel = this.studentLanguageService.getById(dto.getId()).orElse(new StudentLanguageModel());

        return (StudentLanguageModel) ReflectionUtils.mapObject(studentLanguageModel, dto, true, dto.getId() != null);
    }
}
