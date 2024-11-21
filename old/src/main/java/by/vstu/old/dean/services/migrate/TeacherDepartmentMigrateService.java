package by.vstu.old.dean.services.migrate;

import by.vstu.dean.repo.TeacherDepartmentMergeRepository;
import by.vstu.dean.services.AbsenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherDepartmentMigrateService implements IMigrateExecutor {

    private final TeacherDepartmentMergeRepository teacherDepartmentMergeRepository;
    private final AbsenceService absenceService;


    @Override
    public void migrate() {
//        System.err.println(this.getClass().getName());
//        this.absenceService.getAll().forEach((absenceModel) -> {
//
//            TeacherDepartmentMerge tdm = this.teacherDepartmentMergeRepository.findByDepartmentIdAndTeacherId(absenceModel.getDepartment().getId(), absenceModel.getTeacherModel().getId());
//
//            if (tdm == null) {
//
//                TeacherDepartmentMerge tdmNew = new TeacherDepartmentMerge();
//
//                tdmNew.setTeacher(absenceModel.getTeacherModel());
//                tdmNew.setDepartment(absenceModel.getDepartment());
//                tdmNew.setSourceId(absenceModel.getId());
//                tdmNew.setStatus(EStatus.ACTIVE);
//                tdmNew.setCreated(LocalDateTime.now());
//                tdmNew.setUpdated(LocalDateTime.now());
//
//                this.teacherDepartmentMergeRepository.saveAndFlush(tdmNew);
//            }
//
//
//        });


    }

    @Override
    public void init() {

    }

    @Override
    public void cleanup() {

    }
}
