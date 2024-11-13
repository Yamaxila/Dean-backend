package by.vstu.old.dean.services.migrate;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.models.lessons.StudyPlanModel;
import by.vstu.dean.models.merge.TeacherDepartmentMerge;
import by.vstu.dean.repo.TeacherDepartmentMergeRepository;
import by.vstu.dean.services.*;
import by.vstu.old.dean.models.DStudyPlan;
import by.vstu.old.dean.repo.DStudyPlanModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyPlanMigrateService extends BaseMigrateService<StudyPlanModel, DStudyPlan> {

    private final DStudyPlanModelRepository dStudyPlanModelRepository;
    private final StudyPlanService studyPlanService;

    private final GroupService groupService;
    private final ExamTypeService examTypeService;
    private final DisciplineService disciplineService;
    private final TeacherService teacherService;
    private final TeacherDepartmentMergeRepository teacherDepartmentMergeRepository;

    private static final List<TeacherDepartmentMerge> teacherDepartmentMerges = new ArrayList<>();

    @Override
    public Long getLastDBId() {
        return this.studyPlanService.getRepo().findTopByOrderByIdDesc() == null ? 0 : this.studyPlanService.getRepo().findTopByOrderByIdDesc().getSourceId();
    }

    @Override
    public List<StudyPlanModel> convertNotExistsFromDB() {
        List<StudyPlanModel> out = new ArrayList<>();

        this.groupService.getAll().forEach((group) -> {
            List<DStudyPlan> temp = this.dStudyPlanModelRepository.findAllByGroupIdAndIdNotIn(group.getSourceId(), this.studyPlanService.getAllDistinctSourceIdsForGroup(group.getId()));

            if (!temp.isEmpty())
                out.addAll(this.convertList(temp));
        });

        return out;
    }

    @Override
    public StudyPlanModel convertSingle(DStudyPlan dStudyPlan, boolean update) {
//        if (teacherDepartmentMerges.isEmpty())
//            teacherDepartmentMerges.addAll(this.teacherDepartmentMergeRepository.findAll());

        StudyPlanModel studyPlan = new StudyPlanModel();

        studyPlan.setGroup(this.groupService.getBySourceId(dStudyPlan.getGroupId()));

        if (dStudyPlan.getNdis() != null)
            studyPlan.setDiscipline(this.disciplineService.getBySourceId(Long.valueOf(dStudyPlan.getNdis())));

        if (dStudyPlan.getTeacherId() != null)
            studyPlan.setTeacher(this.teacherService.getBySourceId(dStudyPlan.getTeacherId()));

        if (dStudyPlan.getExam() != null)
            studyPlan.setExam(this.examTypeService.getBySourceId(dStudyPlan.getExam().getId()));

        studyPlan.setSemester(dStudyPlan.getSemester());
        studyPlan.setSemesterNumber(dStudyPlan.getSemesterNumber());

        studyPlan.setYearEnd(dStudyPlan.getYearEnd());
        studyPlan.setYearStart(dStudyPlan.getYearStart());
        studyPlan.setCourse(dStudyPlan.getCourse());

        studyPlan.setSourceId(dStudyPlan.getId());

        if (studyPlan.getDiscipline() == null || studyPlan.getTeacher() == null || studyPlan.getExam() == null)
            studyPlan.setStatus(EStatus.BROKEN);
        else
            studyPlan.setStatus(studyPlan.getGroup().getStatus());

        if (!update)
            studyPlan.setCreated(LocalDateTime.now());
        studyPlan.setUpdated(LocalDateTime.now());

        return studyPlan;
    }


    @Override
    public List<StudyPlanModel> convertList(List<DStudyPlan> t) {
        List<StudyPlanModel> out = new ArrayList<>();
        t.forEach(plan -> out.add(this.convertSingle(plan)));

//        List<TeacherDepartmentMerge> tdms = new ArrayList<>();
//        teacherDepartmentMerges.forEach((tdm) -> {
//            if (tdms.stream().noneMatch(p -> p.getTeacher().getId().equals(tdm.getTeacher().getId()) && p.getDepartment().getId().equals(tdm.getDepartment().getId())))
//                tdms.add(tdm);
//        });
//
//        this.teacherDepartmentMergeRepository.saveAll(tdms.stream().distinct().toList());
        return out;
    }

    @Override
    public StudyPlanModel insertSingle(StudyPlanModel t) {
        return this.studyPlanService.save(t);
    }

    @Override
    public List<StudyPlanModel> insertAll(List<StudyPlanModel> t) {
        return this.studyPlanService.saveAll(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }

    @Override
    public void cleanup() {
        teacherDepartmentMerges.clear();
    }
}
