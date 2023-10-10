package by.vstu.dean.services.migrate;

import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.models.lessons.DepartmentModel;
import by.vstu.dean.future.models.lessons.TeacherModel;
import by.vstu.dean.future.models.merge.TeacherDepartmentMerge;
import by.vstu.dean.future.repo.TeacherDepartmentMergeRepository;
import by.vstu.dean.services.AbsenceService;
import by.vstu.dean.services.DepartmentService;
import by.vstu.dean.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherDepartmentMigrateService implements IMigrateExecutor {

    private final TeacherDepartmentMergeRepository teacherDepartmentMergeRepository;
    private final TeacherService teacherService;
    private final DepartmentService departmentService;
    private final AbsenceService absenceService;

    private final List<TeacherModel> teachers = new ArrayList<>();
    private final List<DepartmentModel> departments = new ArrayList<>();
    private final List<TeacherDepartmentMerge> tdms = new ArrayList<>();

    private void load() {
        this.teachers.addAll(this.teacherService.getAll());
        this.departments.addAll(this.departmentService.getAll());
        this.tdms.addAll(this.teacherDepartmentMergeRepository.findAll());
    }


    @Override
    public void migrate() {
        this.load();

        List<TeacherDepartmentMerge> tdmsLocal = new ArrayList<>();

        this.absenceService.getAll().forEach((absenceModel) -> {

            TeacherDepartmentMerge tdm = this.teacherDepartmentMergeRepository.findByDepartmentIdAndTeacherId(absenceModel.getDepartment().getId(), absenceModel.getTeacherModel().getId());

            if(tdm == null) {

                TeacherDepartmentMerge tdmNew = new TeacherDepartmentMerge();

                tdmNew.setTeacher(absenceModel.getTeacherModel());
                tdmNew.setDepartment(absenceModel.getDepartment());
                tdmNew.setSourceId(absenceModel.getId());
                tdmNew.setStatus(EStatus.ACTIVE);
                this.teacherDepartmentMergeRepository.saveAndFlush(tdmNew);
            }


        });





    }
}
