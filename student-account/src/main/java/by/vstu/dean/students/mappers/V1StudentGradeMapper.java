package by.vstu.dean.students.mappers;

import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.models.lessons.AbsenceModel;
import by.vstu.dean.models.merge.StatementStudentMerge;
import by.vstu.dean.students.dtos.StudentAbsenceDTO;
import by.vstu.dean.students.dtos.StudentGradeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface V1StudentGradeMapper extends BaseMapperInterface<StudentGradeDTO, StatementStudentMerge> {
    @Override
    default StudentGradeDTO toDto(StatementStudentMerge entity){
        return entity == null ? null : (StudentGradeDTO) ReflectionUtils.mapObject(entity, new StudentGradeDTO(), false, false);
    }
}