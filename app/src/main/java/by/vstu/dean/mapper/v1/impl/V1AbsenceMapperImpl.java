package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.V1AbsenceDTO;
import by.vstu.dean.mapper.v1.*;
import by.vstu.dean.models.lessons.AbsenceModel;
import by.vstu.dean.services.AbsenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class V1AbsenceMapperImpl implements V1AbsenceMapper {

    @Autowired
    private V1DisciplineMapper disciplineMapper;
    @Autowired
    private V1DepartmentMapper departmentMapper;
    @Autowired
    private V1TeacherMapper teacherMapper;
    @Autowired
    private V1StudentMapper studentMapper;
    @Autowired
    private AbsenceService absenceService;

    @Override
    public AbsenceModel toEntity(V1AbsenceDTO dto) {
        if (dto == null) {
            return null;
        }

        AbsenceModel absenceModel = new AbsenceModel();

        if(dto.getId() != null)
            absenceModel = this.absenceService.getById(dto.getId()).orElse(new AbsenceModel());

        absenceModel = (AbsenceModel) ReflectionUtils.mapObject(absenceModel, dto, true, dto.getId() != null);

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
    public V1AbsenceDTO toDto(AbsenceModel entity) {
        if(entity == null)
            return null;

        V1AbsenceDTO absenceDTO = V1AbsenceMapper.super.toDto(entity);

        absenceDTO.setDiscipline(this.disciplineMapper.toDto(entity.getDiscipline()));
        absenceDTO.setDepartment(this.departmentMapper.toDto(entity.getDepartment()));
        absenceDTO.setTeacher(this.teacherMapper.toDto(entity.getTeacherModel()));
        absenceDTO.setStudent(this.studentMapper.toDto(entity.getStudent()));

        return absenceDTO;
    }

    @Override
    public AbsenceModel partialUpdate(V1AbsenceDTO dto, AbsenceModel entity) {
        if(dto == null)
            return null;

        entity = V1AbsenceMapper.super.partialUpdate(dto, entity);

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
