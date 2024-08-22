package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.AbsenceDTO;
import by.vstu.dean.mapper.v1.*;
import by.vstu.dean.models.lessons.AbsenceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AbsenceMapperImpl implements AbsenceMapper {

    @Autowired
    private DisciplineMapper disciplineMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private TeacherMapper teacherMapper;
    @Autowired
    private StudentMapper studentMapper;

    @Override
    public AbsenceModel toEntity(AbsenceDTO dto) {
        if (dto == null) {
            return null;
        }

        AbsenceModel absenceModel = new AbsenceModel();

        absenceModel = (AbsenceModel) ReflectionUtils.mapObject(absenceModel, dto, true, false);

        if (dto.getDiscipline() != null) {
            absenceModel.setDiscipline(this.disciplineMapper.toEntity(dto.getDiscipline()));
        }
        if (dto.getDepartment() != null) {
            absenceModel.setDepartment(this.departmentMapper.toEntity(dto.getDepartment()));
        }
        if (dto.getTeacher() != null) {
            absenceModel.setTeacherModel(this.teacherMapper.toEntity(dto.getTeacher()));
        }
        if (dto.getStudent() != null) {
            absenceModel.setStudent(this.studentMapper.toEntity(dto.getStudent()));
        }

        return absenceModel;
    }

    @Override
    public AbsenceDTO toDto(AbsenceModel entity) {
        if(entity == null)
            return null;

        AbsenceDTO absenceDTO = AbsenceMapper.super.toDto(entity);

        absenceDTO.setDiscipline(this.disciplineMapper.toDto(entity.getDiscipline()));
        absenceDTO.setDepartment(this.departmentMapper.toDto(entity.getDepartment()));
        absenceDTO.setTeacher(this.teacherMapper.toDto(entity.getTeacherModel()));
        absenceDTO.setStudent(this.studentMapper.toDto(entity.getStudent()));

        return absenceDTO;
    }

    @Override
    public AbsenceModel partialUpdate(AbsenceDTO dto, AbsenceModel entity) {
        if(dto == null)
            return null;

        entity = AbsenceMapper.super.partialUpdate(dto, entity);

        if (dto.getDiscipline() != null) {
            entity.setDiscipline(this.disciplineMapper.toEntity(dto.getDiscipline()));
        }
        if (dto.getDepartment() != null) {
            entity.setDepartment(this.departmentMapper.toEntity(dto.getDepartment()));
        }
        if (dto.getTeacher() != null) {
            entity.setTeacherModel(this.teacherMapper.toEntity(dto.getTeacher()));
        }
        if (dto.getStudent() != null) {
            entity.setStudent(this.studentMapper.toEntity(dto.getStudent()));
        }

        return entity;
    }
}
