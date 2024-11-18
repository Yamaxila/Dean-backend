package by.vstu.dean.timetable.dto.mapper.impl;

import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.mapper.v1.V1ClassroomMapper;
import by.vstu.dean.mapper.v1.V1DisciplineMapper;
import by.vstu.dean.mapper.v1.V1GroupMapper;
import by.vstu.dean.mapper.v1.V1TeacherMapper;
import by.vstu.dean.timetable.dto.LessonDTO;
import by.vstu.dean.timetable.dto.mapper.LessonMapper;
import by.vstu.dean.timetable.models.LessonModel;
import by.vstu.dean.timetable.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class LessonMapperImpl implements LessonMapper {

    private final LessonService lessonService;

    private final V1GroupMapper groupMapper;
    private final V1TeacherMapper teacherMapper;
    private final V1DisciplineMapper disciplineMapper;
    private final V1ClassroomMapper classroomMapper;

    @Override
    public LessonModel toEntity(LessonDTO dto) {
        if (dto == null)
            return null;

        LessonModel lessonModel = new LessonModel();

        if (dto.getId() != null)
            lessonModel = this.lessonService.getById(dto.getId()).orElse(new LessonModel());

        lessonModel = (LessonModel) ReflectionUtils.mapObject(lessonModel, dto, true, dto.getId() != null);

        lessonModel.setGroup(this.groupMapper.toEntity(dto.getGroup()));
        lessonModel.setTeacher(this.teacherMapper.toEntity(dto.getTeacher()));
        lessonModel.setDiscipline(this.disciplineMapper.toEntity(dto.getDiscipline()));
        lessonModel.setRoom(this.classroomMapper.toEntity(dto.getRoom()));

        return null;
    }

    @Override
    public LessonDTO toDto(LessonModel entity) {
        if (entity == null) {
            return null;
        }

        LessonDTO lessonDTO = LessonMapper.super.toDto(entity);

        lessonDTO.setGroup(this.groupMapper.toDto(entity.getGroup()));
        lessonDTO.setTeacher(this.teacherMapper.toDto(entity.getTeacher()));
        lessonDTO.setDiscipline(this.disciplineMapper.toDto(entity.getDiscipline()));
        lessonDTO.setRoom(this.classroomMapper.toDto(entity.getRoom()));
        lessonDTO.setVisible(entity.isVisible());

        return LessonMapper.super.toDto(entity);
    }

    @Override
    public LessonModel partialUpdate(LessonDTO dto, LessonModel entity) {
        if (dto == null) {
            return null;
        }

        entity = LessonMapper.super.partialUpdate(dto, entity);

        if (dto.getGroup() != null) {
            entity.setGroup(this.groupMapper.partialUpdate(dto.getGroup(), entity.getGroup()));
        }
        if (dto.getTeacher() != null) {
            entity.setTeacher(this.teacherMapper.partialUpdate(dto.getTeacher(), entity.getTeacher()));
        }
        if (dto.getDiscipline() != null) {
            entity.setDiscipline(this.disciplineMapper.partialUpdate(dto.getDiscipline(), entity.getDiscipline()));
        }
        if (dto.getRoom() != null) {
            entity.setRoom(this.classroomMapper.partialUpdate(dto.getRoom(), entity.getRoom()));
        }

        return entity;
    }
}
