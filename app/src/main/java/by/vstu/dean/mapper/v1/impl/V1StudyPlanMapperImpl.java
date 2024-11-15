package by.vstu.dean.mapper.v1.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.dto.v1.lessons.V1StudyPlanDTO;
import by.vstu.dean.mapper.v1.*;
import by.vstu.dean.models.lessons.StudyPlanModel;
import by.vstu.dean.services.StudyPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class V1StudyPlanMapperImpl implements V1StudyPlanMapper {

    private final StudyPlanService studyPlanService;

    private final V1GroupMapper groupMapper;

    private final V1ExamTypeMapper examTypeMapper;

    private final V1DisciplineMapper disciplineMapper;

    private final V1TeacherMapper teacherMapper;

    public StudyPlanModel toEntity(V1StudyPlanDTO dto) {

        if (dto == null)
            return null;

        StudyPlanModel studyPlanModel = new StudyPlanModel();

        if (dto.getId() != null)
            studyPlanModel = this.studyPlanService.getById(dto.getId()).orElse(new StudyPlanModel());

        studyPlanModel = (StudyPlanModel) ReflectionUtils.mapObject(studyPlanModel, dto, true, dto.getId() != null);

        studyPlanModel.setGroup(this.groupMapper.toEntity(dto.getGroup()));
        studyPlanModel.setExam(this.examTypeMapper.toEntity(dto.getExam()));
        studyPlanModel.setDiscipline(this.disciplineMapper.toEntity(dto.getDiscipline()));
        studyPlanModel.setTeacher(this.teacherMapper.toEntity(dto.getTeacher()));

        return studyPlanModel;
    }

    public V1StudyPlanDTO toDto(StudyPlanModel entity) {
        if (entity == null) {
            return null;
        }

        V1StudyPlanDTO studyPlanDTO = V1StudyPlanMapper.super.toDto(entity);

        studyPlanDTO.setGroup(this.groupMapper.toDto(entity.getGroup()));
        studyPlanDTO.setExam(this.examTypeMapper.toDto(entity.getExam()));
        studyPlanDTO.setDiscipline(this.disciplineMapper.toDto(entity.getDiscipline()));
        studyPlanDTO.setTeacher(this.teacherMapper.toDto(entity.getTeacher()));

        return studyPlanDTO;
    }

    public StudyPlanModel partialUpdate(V1StudyPlanDTO dto, StudyPlanModel entity) {
        if (dto == null) {
            return null;
        }

        entity = V1StudyPlanMapper.super.partialUpdate(dto, entity);

        if (dto.getGroup() != null) {
            entity.setGroup(this.groupMapper.partialUpdate(dto.getGroup(), entity.getGroup()));
        }
        if (dto.getExam() != null) {
            entity.setExam(this.examTypeMapper.partialUpdate(dto.getExam(), entity.getExam()));
        }
        if (dto.getDiscipline() != null) {
            entity.setDiscipline(this.disciplineMapper.partialUpdate(dto.getDiscipline(), entity.getDiscipline()));
        }
        if (dto.getTeacher() != null) {
            entity.setTeacher(this.teacherMapper.partialUpdate(dto.getTeacher(), entity.getTeacher()));
        }

        return entity;
    }
}
