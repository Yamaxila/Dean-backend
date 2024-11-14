package by.vstu.migrate.v1.service.migrate;

import by.vstu.dean.models.lessons.AbsenceModel;
import by.vstu.dean.services.AbsenceService;
import by.vstu.dean.services.DepartmentService;
import by.vstu.dean.services.DisciplineService;
import by.vstu.dean.services.TeacherService;
import by.vstu.dean.services.students.StudentService;
import by.vstu.migrate.v1.models.lessons.V1AbsenceModel;
import by.vstu.migrate.v1.repo.V1AbsenceModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AbsenceMigrateService extends BaseMigrateService<AbsenceModel, V1AbsenceModel> {

    private final AbsenceService absenceService;
    private final V1AbsenceModelRepository v1AbsenceModelRepository;

    private final DisciplineService disciplineService;
    private final DepartmentService departmentService;
    private final TeacherService teacherService;
    private final StudentService studentService;

    @Override
    public List<AbsenceModel> convertNotExistsFromDB() {
        return this.convertList(v1AbsenceModelRepository.findAll());
    }

    @Override
    public AbsenceModel convertSingle(V1AbsenceModel v1AbsenceModel, boolean update) {
        AbsenceModel a = this.convertSingle(v1AbsenceModel);

        this.disciplineService.getById(v1AbsenceModel.getDiscipline().getId()).ifPresent(a::setDiscipline);

        if (a.getDiscipline() == null) {
            throw new RuntimeException("Discipline for absence with id = %d not found".formatted(v1AbsenceModel.getId()));
        }

        this.departmentService.getById(a.getDepartment().getId()).ifPresent(a::setDepartment);

        if (a.getDepartment() == null) {
            throw new RuntimeException("Department for absence with id = %d not found".formatted(a.getId()));
        }

        this.teacherService.getById(v1AbsenceModel.getTeacherModel().getId()).ifPresent(a::setTeacherModel);

        if (a.getTeacherModel() == null) {
            throw new RuntimeException("Teacher for absence with id = %d not found".formatted(a.getId()));
        }

        a.setLessonType(v1AbsenceModel.getLessonType().map());
        a.setLessonNumber(v1AbsenceModel.getLessonNumber());

        this.studentService.getById(v1AbsenceModel.getStudent().getId()).ifPresent(a::setStudent);

        if (a.getStudent() == null) {
            throw new RuntimeException("Student for absence with id = %d not found".formatted(a.getId()));
        }

        a.setAbsenceTime(v1AbsenceModel.getAbsenceTime());
        a.setHourPrice(v1AbsenceModel.getHourPrice());
        a.setPaymentDate(v1AbsenceModel.getPaymentDate());
        a.setDateCompleted(v1AbsenceModel.getDateCompleted());
        a.setDatePrint(v1AbsenceModel.getDatePrint());
        a.setReasonMsg(v1AbsenceModel.getReasonMsg());
        a.setDateErip(v1AbsenceModel.getDateErip());
        a.setPrinted(v1AbsenceModel.isPrinted());

        return a;
    }

    @Override
    public AbsenceModel insertSingle(AbsenceModel t) {
        return this.absenceService.save(t);
    }

    @Override
    public List<AbsenceModel> insertAll(List<AbsenceModel> t) {
        return this.absenceService.saveAll(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
