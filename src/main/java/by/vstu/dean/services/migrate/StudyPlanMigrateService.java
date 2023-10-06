package by.vstu.dean.services.migrate;

import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.models.lessons.DisciplineModel;
import by.vstu.dean.future.models.lessons.ExamModel;
import by.vstu.dean.future.models.lessons.StudyPlanModel;
import by.vstu.dean.future.models.lessons.TeacherModel;
import by.vstu.dean.future.models.merge.TeacherDepartmentMerge;
import by.vstu.dean.future.models.students.GroupModel;
import by.vstu.dean.future.repo.*;
import by.vstu.dean.old.OldDBBaseModel;
import by.vstu.dean.old.models.DStudyPlan;
import by.vstu.dean.old.models.DTeacherModel;
import by.vstu.dean.old.repo.DStudyPlanModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyPlanMigrateService extends BaseMigrateService<StudyPlanModel, DStudyPlan> {

    private final DStudyPlanModelRepository dStudyPlanModelRepository;
    private final StudyPlanModelRepository studyPlanRepository;

    private final GroupModelRepository groupModelRepository;
    private final ExamModelRepository examModelRepository;
    private final DisciplineModelRepository disciplineModelRepository;
    private final TeacherModelRepository teacherModelRepository;
    private final TeacherDepartmentMergeRepository teacherDepartmentMergeRepository;

    private final TeacherMigrateService teacherMigrateService;

    private final List<DisciplineModel> disciplines = new ArrayList<>();
    private final List<ExamModel> examTypes = new ArrayList<>();
    private final List<GroupModel> groups = new ArrayList<>();
    private final List<TeacherModel> teachers = new ArrayList<>();

    @Override
    public Long getLastDBId() {
        return this.studyPlanRepository.findTopByOrderByIdDesc() == null ? 0 : this.studyPlanRepository.findTopByOrderByIdDesc().getSourceId();
    }

    @Override
    public List<StudyPlanModel> convertNotExistsFromDB() {

        List<OldDBBaseModel> bases = this.dStudyPlanModelRepository.findAllByIdAfter(this.getLastDBId());
        List<DStudyPlan> temp = new ArrayList<>();

        bases.forEach(base -> temp.add((DStudyPlan) base));

        if (!temp.isEmpty()) {
            this.examTypes.addAll(this.examModelRepository.findAll());
            this.groups.addAll(this.groupModelRepository.findAll());
            this.disciplines.addAll(this.disciplineModelRepository.findAll());

            this.teacherMigrateService.insertAll(this.teacherMigrateService.convertNotExistsFromDB(this.findUniqueTeachers()));

            this.teachers.addAll(this.teacherModelRepository.findAll());


            return this.convertList(temp).stream().filter(p -> p.getGroup() != null && p.getTeacher() != null).toList();
        }


        return new ArrayList<>();
    }

    @Override
    public StudyPlanModel convertSingle(DStudyPlan dStudyPlan) {
        StudyPlanModel studyPlan = new StudyPlanModel();

        ExamModel exam = examTypes.stream().filter(p -> dStudyPlan.getExam() != null && p.getSourceId().equals(dStudyPlan.getExam().getId())).findFirst().orElse(null);
        GroupModel group = groups.stream().filter(p -> dStudyPlan.getGroup() != null && p.getSourceId().equals(dStudyPlan.getGroup().getId())).findFirst().orElse(null);
        DisciplineModel discipline = disciplines.stream().filter(p -> p.getSourceId().equals(Long.valueOf(dStudyPlan.getNdis()))).findFirst().orElse(null);
        TeacherModel teacher = teachers.stream().filter(p -> dStudyPlan.getTeacher() != null && p.getSourceId().equals(dStudyPlan.getTeacher().getId())).findFirst().orElse(null);

        if (exam != null)
            studyPlan.setExam(exam);

        studyPlan.setSemester(dStudyPlan.getSemester());

        if (group != null)
            studyPlan.setGroup(group);

        studyPlan.setYearEnd(dStudyPlan.getYearEnd());
        studyPlan.setYearStart(dStudyPlan.getYearStart());
        studyPlan.setCourse(dStudyPlan.getCourse());

        if (discipline != null)
            studyPlan.setDiscipline(discipline);

        studyPlan.setSemesterNumber(dStudyPlan.getSemesterNumber());

        if (teacher != null) {
            studyPlan.setTeacher(teacher);

            if (discipline != null && discipline.getDepartment() != null) {
                long departmentId = discipline.getDepartment().getId();

                departmentId = switch ((int) departmentId) {
                    case 31 -> 15L;
                    case 27 -> 7L;
                    case 11 -> 26L;
                    default -> departmentId;
                };

                if (this.teacherDepartmentMergeRepository.findByDepartmentIdAndTeacherId(departmentId, teacher.getId()) == null) {
                    TeacherDepartmentMerge tdm = new TeacherDepartmentMerge(teacher, discipline.getDepartment());
                    tdm.setSourceId(dStudyPlan.getId());
                    tdm.setStatus(EStatus.ACTIVE);
//                    System.out.printf("Found department %s for teacher %s\n", discipline.getDepartment().getId(), teacher.getId());
                    this.teacherDepartmentMergeRepository.saveAndFlush(tdm);
                }
            }
        }

        studyPlan.setSourceId(dStudyPlan.getId());
        studyPlan.setStatus(EStatus.DELETED);
        return studyPlan;
    }

    private List<DTeacherModel> findUniqueTeachers() {
        List<DTeacherModel> teachers = new ArrayList<>();
        this.groupModelRepository.findAll().forEach((group) -> {

            teachers.addAll(this.dStudyPlanModelRepository.findAllByGroupIdAndTeacherIdNotNull(group.getSourceId()).stream().map(DStudyPlan::getTeacher).distinct().toList());

        });
        return teachers.stream().distinct().toList();
    }

    @Override
    public List<StudyPlanModel> convertList(List<DStudyPlan> t) {
        List<StudyPlanModel> out = new ArrayList<>();
        t.forEach(plan -> out.add(this.convertSingle(plan)));
        return out;
    }

    @Override
    public StudyPlanModel insertSingle(StudyPlanModel t) {
        return this.studyPlanRepository.saveAndFlush(t);
    }

    @Override
    public List<StudyPlanModel> insertAll(List<StudyPlanModel> t) {
        return this.studyPlanRepository.saveAllAndFlush(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
