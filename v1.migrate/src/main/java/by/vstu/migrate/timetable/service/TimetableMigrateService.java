package by.vstu.migrate.timetable.service;

import by.vstu.dean.services.ClassroomService;
import by.vstu.dean.services.DisciplineService;
import by.vstu.dean.services.GroupService;
import by.vstu.dean.services.TeacherService;
import by.vstu.dean.timetable.models.LessonModel;
import by.vstu.dean.timetable.service.LessonService;
import by.vstu.migrate.timetable.models.V1LessonModel;
import by.vstu.migrate.timetable.repo.V1LessonModelRepository;
import by.vstu.migrate.v1.repo.V1ClassroomModelRepository;
import by.vstu.migrate.v1.repo.V1DisciplineModelRepository;
import by.vstu.migrate.v1.repo.V1GroupModelRepository;
import by.vstu.migrate.v1.repo.V1TeacherModelRepository;
import by.vstu.migrate.v1.service.migrate.BaseMigrateService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimetableMigrateService extends BaseMigrateService<LessonModel, V1LessonModel> {

    private final LessonService lessonService;
    private final V1LessonModelRepository v1LessonModelRepository;

    private final GroupService groupService;
    private final V1GroupModelRepository v1GroupModelRepository;

    private final TeacherService teacherService;
    private final V1TeacherModelRepository v1TeacherModelRepository;

    private final DisciplineService disciplineService;
    private final V1DisciplineModelRepository v1DisciplineModelRepository;

    private final ClassroomService classroomService;
    private final V1ClassroomModelRepository v1ClassroomModelRepository;

    @Override
    public List<LessonModel> convertNotExistsFromDB() {
        return this.convertList(this.v1LessonModelRepository.findAll());
    }

    @Override
    public LessonModel convertSingle(V1LessonModel v1LessonModel, boolean update) {
        LessonModel l = new LessonModel();
        l.setSourceId(v1LessonModel.getId());
        l.setStatus(v1LessonModel.getStatus().map());
        l.setCreated(v1LessonModel.getCreated());
        l.setUpdated(v1LessonModel.getUpdated());

        this.v1GroupModelRepository.findById(v1LessonModel.getGroupId()).ifPresent(group ->
                l.setGroup(this.groupService.getBySourceId(group.getSourceId()))
        );
        this.v1TeacherModelRepository.findById(v1LessonModel.getTeacherId()).ifPresent(teacher ->
                l.setTeacher(this.teacherService.getBySourceId(teacher.getSourceId()))
        );
        this.v1DisciplineModelRepository.findById(v1LessonModel.getDisciplineId()).ifPresent(discipline ->
                l.setDiscipline(this.disciplineService.getBySourceId(discipline.getSourceId()))
        );
        this.v1ClassroomModelRepository.findById(v1LessonModel.getRoomId()).ifPresent(classroom ->
                l.setRoom(this.classroomService.getBySourceId(classroom.getId()))
        );

        l.setLessonNumber(v1LessonModel.getLessonNumber());
        l.setDay(v1LessonModel.getDay());
        l.setLessonType(v1LessonModel.getLessonType().map());
        l.setWeekType(v1LessonModel.getWeekType().map());
        l.setSubGroup(v1LessonModel.getSubGroup().map());
        l.setStartDate(v1LessonModel.getStartDate());
        l.setEndDate(v1LessonModel.getEndDate());
        l.setVisible(v1LessonModel.isVisible());

        return l;
    }

    @Override
    public LessonModel insertSingle(LessonModel t) {
        return this.lessonService.save(t);
    }

    @Override
    public List<LessonModel> insertAll(List<LessonModel> t) {
        return this.lessonService.saveAll(t);
    }

    @Override
    @PostConstruct
    public void migrate() {
        new Thread(() -> {
            System.err.println(this.getClass().getName());
            this.insertAll(this.convertNotExistsFromDB());
        }).start();
    }
}
