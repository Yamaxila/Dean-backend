package by.vstu.dean.services.migrate;

import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.models.lessons.DisciplineModel;
import by.vstu.dean.future.models.lessons.ExamModel;
import by.vstu.dean.future.models.lessons.StudyPlan;
import by.vstu.dean.future.models.lessons.TeacherModel;
import by.vstu.dean.future.models.merge.TeacherDepartmentMerge;
import by.vstu.dean.future.models.students.GroupModel;
import by.vstu.dean.future.repo.*;
import by.vstu.dean.old.OldDBBaseModel;
import by.vstu.dean.old.models.DStudyPlan;
import by.vstu.dean.old.models.DTeacherModel;
import by.vstu.dean.old.repo.DStudyPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyPlanMigrateService extends BaseMigrateService<StudyPlan, DStudyPlan> {

    private final DStudyPlanRepository dStudyPlanRepository;
    private final StudyPlanRepository studyPlanRepository;

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
    public List<StudyPlan> convertNotExistsFromDB() {

        List<OldDBBaseModel> bases = this.dStudyPlanRepository.findAllByIdAfter(this.getLastDBId());
        List<DStudyPlan> temp = new ArrayList<>();

        bases.forEach(base -> temp.add((DStudyPlan)base));

        if(!temp.isEmpty()) {
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
    public StudyPlan convertSingle(DStudyPlan dStudyPlan) {
        StudyPlan studyPlan = new StudyPlan();

        ExamModel exam = examTypes.stream().filter(p -> dStudyPlan.getExam() != null && p.getSourceId().equals(dStudyPlan.getExam().getId())).findFirst().orElse(null);
        GroupModel group = groups.stream().filter(p -> dStudyPlan.getGroup() != null && p.getSourceId().equals(dStudyPlan.getGroup().getId())).findFirst().orElse(null);
        DisciplineModel discipline = disciplines.stream().filter(p -> p.getSourceId().equals(Long.valueOf(dStudyPlan.getNdis()))).findFirst().orElse(null);
        TeacherModel teacher = teachers.stream().filter(p -> dStudyPlan.getTeacher() != null && p.getSourceId().equals(dStudyPlan.getTeacher().getId())).findFirst().orElse(null);

        if(exam != null)
            studyPlan.setExam(exam);

        studyPlan.setSemester(dStudyPlan.getSemester());

        if(group != null)
            studyPlan.setGroup(group);

        studyPlan.setYearEnd(dStudyPlan.getYearEnd());
        studyPlan.setYearStart(dStudyPlan.getYearStart());
        studyPlan.setCourse(dStudyPlan.getCourse());

        if(discipline != null)
            studyPlan.setDiscipline(discipline);

        studyPlan.setSemesterNumber(dStudyPlan.getSemesterNumber());

        if(teacher != null) {
            studyPlan.setTeacher(teacher);

            if(discipline != null && discipline.getDepartment() != null) {
                if(this.teacherDepartmentMergeRepository.findByDepartmentIdAndTeacherId(discipline.getDepartment().getId(), teacher.getId()) == null) {
                    TeacherDepartmentMerge tdm = new TeacherDepartmentMerge(teacher, discipline.getDepartment());
                    tdm.setSourceId(dStudyPlan.getId());
                    tdm.setStatus(EStatus.ACTIVE);
                    System.out.printf("Found department %s for teacher %s\n", discipline.getDepartment().getId(), teacher.getId());
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

            teachers.addAll(this.dStudyPlanRepository.findAllByGroupIdAndTeacherIdNotNull(group.getSourceId()).stream().map(DStudyPlan::getTeacher).distinct().toList());

        });
        return teachers.stream().distinct().toList();
    }

    @Override
    public List<StudyPlan> convertList(List<DStudyPlan> t) {
        List<StudyPlan> out = new ArrayList<>();
        t.forEach(plan -> out.add(this.convertSingle(plan)));
        return out;
    }

    @Override
    public StudyPlan insertSingle(StudyPlan t) {
        return this.studyPlanRepository.saveAndFlush(t);
    }

    @Override
    public List<StudyPlan> insertAll(List<StudyPlan> t) {
        return this.studyPlanRepository.saveAllAndFlush(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
