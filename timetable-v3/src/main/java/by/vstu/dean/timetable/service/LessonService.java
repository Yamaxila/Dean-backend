package by.vstu.dean.timetable.service;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.core.websocket.WSControllerManager;
import by.vstu.dean.enums.ELessonType;
import by.vstu.dean.services.*;
import by.vstu.dean.timetable.dto.RoomDTO;
import by.vstu.dean.timetable.enums.EWeekType;
import by.vstu.dean.timetable.models.LessonModel;
import by.vstu.dean.timetable.repo.LessonModelRepository;
import org.javers.core.Javers;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LessonService extends BaseService<LessonModel, LessonModelRepository> {

    protected final GroupService groupService;
    protected final TeacherService teacherService;
    protected final DisciplineService disciplineService;
    protected final DepartmentService departmentService;
    protected final ClassroomService classroomService;

    public LessonService(LessonModelRepository repo, Javers javers, WSControllerManager wsControllerManager, GroupService groupService, TeacherService teacherService, DisciplineService disciplineService, DepartmentService departmentService, ClassroomService classroomService) {
        super(repo, javers, wsControllerManager);
        this.groupService = groupService;
        this.teacherService = teacherService;
        this.disciplineService = disciplineService;
        this.departmentService = departmentService;
        this.classroomService = classroomService;
    }

    @Override
    public LessonModel delete(Long id) {
        Optional<LessonModel> l = this.repo.findById(id);

        if (l.isEmpty())
            return null;

        LessonModel lesson = l.get();
        lesson.setStatus(EStatus.DELETED);
        lesson.setVisible(false);

        return this.repo.saveAndFlush(lesson);
    }

    public List<LessonModel> getByGroupDisciplineTeacher(long groupId, long disciplineId, long teacherId) {
        return this.repo.findByGroup_IdAndDiscipline_IdAndTeacher_IdAndStatus(groupId, disciplineId, teacherId, EStatus.ACTIVE);
    }

    public List<LessonModel> setVisibility(Long groupId, Boolean visible, String date1, String date2) {
        LocalDate dateFrom = LocalDate.parse(date1);
        LocalDate dateTo = LocalDate.parse(date2);
        List<LessonModel> lessons = this.repo.findByGroupIdAndStatusAndStartDateBetweenAndEndDateBetween(groupId, EStatus.ACTIVE, dateFrom, dateTo);
        lessons.forEach(l -> l.setVisible(visible));

        return this.saveAll(lessons);
    }

    public List<LessonModel> getHiddenGroups() {
        return this.repo.findByStatusAndVisible(EStatus.ACTIVE, false);
    }

    public LessonModel update(LessonModel old, RoomDTO dto) {

        if (dto.getRoomId() != null)
            this.classroomService.getById(dto.getRoomId()).ifPresent(old::setRoom);

        if (dto.getDay() != null)
            old.setDay(dto.getDay());

        if (dto.getTeacherId() != null)
            this.teacherService.getById(dto.getTeacherId()).ifPresent(old::setTeacher);

        if (dto.getWeekType() != null)
            old.setWeekType(EWeekType.valueOf(dto.getWeekType()));

        if (dto.getSubGroup() != null)
            old.setSubGroup(dto.getSubGroup());

        if (dto.getLessonType() != null)
            old.setLessonType(ELessonType.valueOf(dto.getLessonType()));

        if (dto.getLessonNumber() != null)
            old.setLessonNumber(dto.getLessonNumber());

        if (dto.getGroupId() != null)
            this.groupService.getById(dto.getGroupId()).ifPresent(old::setGroup);

        if (dto.getDisciplineId() != null)
            this.disciplineService.getById(dto.getDisciplineId()).ifPresent(old::setDiscipline);

        if (dto.getStartDate() != null)
            old.setStartDate(dto.getStartDate());

        if (dto.getEndDate() != null)
            old.setEndDate(dto.getEndDate());

        if (old.getStartDate().equals(old.getEndDate()) && (old.getStartDate().getDayOfWeek().getValue() - 1 != old.getDay())) {
            old.setDay((short) (old.getStartDate().getDayOfWeek().getValue() - 1));
        }

        if (dto.getVisible() != null)
            old.setVisible(dto.getVisible());

        old.setUpdated(LocalDateTime.now());

        return old;
    }

    public LessonModel create(RoomDTO dto) {

        LessonModel lessonModel = new LessonModel();

        if (dto.getDay() == null
            || dto.getLessonNumber() == null
            || dto.getGroupId() == null
            || dto.getLessonType() == null
            || dto.getDisciplineId() == null
            || dto.getTeacherId() == null
            || dto.getWeekType() == null
            || dto.getSubGroup() == null
            || dto.getRoomId() == null)
            return null;

        lessonModel.setUpdated(LocalDateTime.now());
        lessonModel.setCreated(LocalDateTime.now());
        lessonModel.setSourceId(null);

        this.groupService.getById(dto.getGroupId()).ifPresent(lessonModel::setGroup);
        this.teacherService.getById(dto.getTeacherId()).ifPresent(lessonModel::setTeacher);
        this.disciplineService.getById(dto.getDisciplineId()).ifPresent(lessonModel::setDiscipline);
        this.classroomService.getById(dto.getRoomId()).ifPresent(lessonModel::setRoom);

        if (lessonModel.getGroup() == null || lessonModel.getTeacher() == null || lessonModel.getDiscipline() == null || lessonModel.getRoom() == null)
            return null;

        lessonModel.setWeekType(EWeekType.valueOf(dto.getWeekType()));
        lessonModel.setSubGroup(dto.getSubGroup());
        lessonModel.setLessonType(ELessonType.valueOf(dto.getLessonType()));
        lessonModel.setLessonNumber(dto.getLessonNumber());
        lessonModel.setDay(dto.getDay());

        if (dto.getStartDate() == null && dto.getEndDate() == null) {
            int halfYear = LocalDate.now().getMonthValue() < 7 ? 1 : 2;
            if (halfYear == 1) {
                lessonModel.setStartDate(LocalDate.of(LocalDate.now().getYear(), 2, 1));
                lessonModel.setEndDate(LocalDate.of(LocalDate.now().getYear(), 5, 31));
            } else {
                lessonModel.setStartDate(LocalDate.of(LocalDate.now().getYear(), 9, 1));
                lessonModel.setEndDate(LocalDate.of(LocalDate.now().getYear(), 12, 31));
            }
        } else if (dto.getStartDate() == null) {
            lessonModel.setStartDate(dto.getEndDate());
            lessonModel.setEndDate(dto.getEndDate());
        } else if (dto.getEndDate() == null) {
            lessonModel.setStartDate(dto.getStartDate());
            lessonModel.setEndDate(dto.getStartDate());
        } else {
            lessonModel.setStartDate(dto.getStartDate());
            lessonModel.setEndDate(dto.getEndDate());
        }
        if (lessonModel.getStartDate().isEqual(lessonModel.getEndDate()) &&
            lessonModel.getStartDate().getDayOfWeek().getValue() != (lessonModel.getDay() + 1)) {
            lessonModel.setDay((short) (lessonModel.getStartDate().getDayOfWeek().getValue() - 1));
        }

        lessonModel.setStatus(EStatus.ACTIVE);
        lessonModel.setVisible(false);

        return lessonModel;
    }

}
