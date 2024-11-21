package by.vstu.migrate.v1.service.migrate;

import by.vstu.dean.models.lessons.StudyPlanModel;
import by.vstu.dean.services.*;
import by.vstu.migrate.v1.models.lessons.V1StudyPlanModel;
import by.vstu.migrate.v1.repo.V1StudyPlanModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudyPlanMigrateService extends BaseMigrateService<StudyPlanModel, V1StudyPlanModel> {

    private final StudyPlanService studyPlanService;
    private final V1StudyPlanModelRepository v1StudyPlanModelRepository;

    private final GroupService groupService;
    private final ExamTypeService examTypeService;
    private final DisciplineService disciplineService;
    private final TeacherService teacherService;

    @Override
    public List<StudyPlanModel> convertNotExistsFromDB() {
        return this.convertList(this.v1StudyPlanModelRepository.findAll());
    }

    @Override
    public StudyPlanModel convertSingle(V1StudyPlanModel v1StudyPlanModel, boolean update) {
        StudyPlanModel spm = new StudyPlanModel();
        spm.setId(v1StudyPlanModel.getId());
        spm.setSourceId(v1StudyPlanModel.getSourceId());
        spm.setStatus(v1StudyPlanModel.getStatus().map());
        spm.setCreated(v1StudyPlanModel.getCreated());
        spm.setUpdated(v1StudyPlanModel.getUpdated());

        this.groupService.getById(v1StudyPlanModel.getGroup().getId()).ifPresent(spm::setGroup);

        if (spm.getGroup() == null) {
            throw new RuntimeException("Group for studyPlan with id = %d not found".formatted(v1StudyPlanModel.getId()));
        }

        spm.setYearStart(v1StudyPlanModel.getYearStart());
        spm.setYearEnd(v1StudyPlanModel.getYearEnd());
        spm.setSemesterNumber(v1StudyPlanModel.getSemesterNumber());
        spm.setSemester(v1StudyPlanModel.getSemester());
        spm.setCourse(v1StudyPlanModel.getCourse());

        this.examTypeService.getById(v1StudyPlanModel.getExam().getId()).ifPresent(spm::setExam);

        if (spm.getExam() == null) {
            throw new RuntimeException("Exam for studyPlan with id = %d not found".formatted(v1StudyPlanModel.getId()));
        }

        this.disciplineService.getById(v1StudyPlanModel.getDiscipline().getId()).ifPresent(spm::setDiscipline);

        if (spm.getDiscipline() == null) {
            throw new RuntimeException("Discipline for studyPlan with id = %d not found".formatted(v1StudyPlanModel.getId()));
        }

        this.teacherService.getById(v1StudyPlanModel.getTeacher().getId()).ifPresent(spm::setTeacher);

        if (spm.getTeacher() == null) {
            throw new RuntimeException("Teacher for studyPlan with id = %d not found".formatted(v1StudyPlanModel.getId()));
        }

        spm.setHours(v1StudyPlanModel.getHours());
        spm.setTestPoints(v1StudyPlanModel.getTestPoints());

        return spm;
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
}
