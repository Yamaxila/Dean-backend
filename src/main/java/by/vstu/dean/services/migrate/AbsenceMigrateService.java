package by.vstu.dean.services.migrate;

import by.vstu.dean.enums.ELessonType;
import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.models.lessons.AbsenceModel;
import by.vstu.dean.future.models.lessons.DepartmentModel;
import by.vstu.dean.future.models.lessons.DisciplineModel;
import by.vstu.dean.future.models.lessons.TeacherModel;
import by.vstu.dean.future.models.students.StudentModel;
import by.vstu.dean.future.repo.AbsenceModelRepository;
import by.vstu.dean.old.models.DAbsenceModel;
import by.vstu.dean.old.repo.DAbsenceModelRepository;
import by.vstu.dean.services.DepartmentService;
import by.vstu.dean.services.DisciplineService;
import by.vstu.dean.services.StudentService;
import by.vstu.dean.services.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AbsenceMigrateService extends BaseMigrateService<AbsenceModel, DAbsenceModel> {

    private final DAbsenceModelRepository dAbsenceModelRepository;
    private final AbsenceModelRepository absenceModelRepository;

    private final DisciplineMigrateService disciplineMigrateService;
    private final TeacherService teacherService;
    private final DisciplineService disciplineService;
    private final DepartmentService departmentService;
    private final StudentService studentService;

    private final List<StudentModel> students = new ArrayList<>();
    private final List<TeacherModel> teachers = new ArrayList<>();
    private final List<DisciplineModel> disciplines = new ArrayList<>();
    private final List<DepartmentModel> departments = new ArrayList<>();

    @Override
    public Long getLastDBId() { // Лучще не брать ниже, ибо в старой базе есть люди с одинаковым номером зачетки
        return this.absenceModelRepository.findTopByOrderByIdDesc() == null ? 83546 : this.absenceModelRepository.findTopByOrderByIdDesc().getSourceId();
    }

    @Override
    public List<AbsenceModel> convertNotExistsFromDB() {
        return this.convertList(this.dAbsenceModelRepository.findAllByIdAfter(this.getLastDBId()));
    }

    @Override
    public AbsenceModel convertSingle(DAbsenceModel dAbsenceModel) {

        if(this.teachers.isEmpty())
            this.teachers.addAll(this.teacherService.getAll());

        if(this.disciplines.isEmpty())
            this.disciplines.addAll(this.disciplineService.getAll());

        if(this.departments.isEmpty())
            this.departments.addAll(this.departmentService.getAll());

        if(this.students.isEmpty())
            this.students.addAll(this.studentService.getAll());

        AbsenceModel absenceModel = new AbsenceModel();

        absenceModel.setAbsenceTime(dAbsenceModel.getClock().doubleValue());
        long dId = dAbsenceModel.getDepartment().getId();
        dId = switch ((int) dId) {
            case 31 -> 15L;
            case 27 -> 7L;
            case 11 -> 26L;
            default -> dId;
        };
        Optional<TeacherModel> teacherModel = this.teachers.stream().filter(p -> p.getSourceId().equals(dAbsenceModel.getTeacher().getId())).findFirst();
        Optional<DisciplineModel> disciplineModel = this.disciplines.stream().filter(p -> p.getSourceId().equals(dAbsenceModel.getDiscipline().getId())).findFirst();
        long finalDId = dId;
        Optional<DepartmentModel> departmentModel = this.departments.stream().filter(p -> p.getSourceId().equals(finalDId)).findFirst();
        Optional<StudentModel> student = this.students.stream().filter(p -> p.getSourceId().equals(dAbsenceModel.getStudent().getId())).findFirst();

        if(teacherModel.isEmpty())
            throw new RuntimeException("Teacher not found for absence with id = " + dAbsenceModel.getId());

        if(disciplineModel.isEmpty())
            throw new RuntimeException("Discipline not found for absence with id = " + dAbsenceModel.getId());

        if(student.isEmpty())
            throw new RuntimeException("Student not found for absence with id = " + dAbsenceModel.getId());

        if(departmentModel.isEmpty())
            throw new RuntimeException("Department not found for absence with id = " + dAbsenceModel.getId());

        absenceModel.setTeacherModel(teacherModel.get());
        absenceModel.setDiscipline(disciplineModel.get());
        absenceModel.setDepartment(departmentModel.get());
        absenceModel.setStudent(student.get());
        absenceModel.setDate(dAbsenceModel.getDate() != null ? dAbsenceModel.getDate().toLocalDate() : null);
        absenceModel.setDateCompleted(dAbsenceModel.getDateCompleted() != null ? dAbsenceModel.getDateCompleted().toLocalDate() : null);
        absenceModel.setDatePrint(dAbsenceModel.getDatePrint() != null ? dAbsenceModel.getDatePrint().toLocalDate() : null);
        absenceModel.setPaymentDate(dAbsenceModel.getDataOpl() != null ? dAbsenceModel.getDataOpl().toLocalDate() : null);
        absenceModel.setDateErip(dAbsenceModel.getDateErip() != null ? dAbsenceModel.getDateErip().toLocalDate() : null);
        absenceModel.setHourPrice(dAbsenceModel.getPrice().doubleValue());
        absenceModel.setReasonMsg(dAbsenceModel.getReasonMsg());
        absenceModel.setPrinted(dAbsenceModel.getPrinted() == 1);

        absenceModel.setLessonType(dAbsenceModel.getLessonType().equalsIgnoreCase("практ.") ? ELessonType.PRACTICE : ELessonType.LAB);
        absenceModel.setLessonNumber(0);
        absenceModel.setStatus(!dAbsenceModel.getCompleted().equalsIgnoreCase("нет") ? EStatus.ACTIVE : EStatus.DELETED);
        absenceModel.setSourceId(dAbsenceModel.getId());

        return absenceModel;
    }

    @Override
    public AbsenceModel insertSingle(AbsenceModel t) {
        return this.absenceModelRepository.saveAndFlush(t);
    }

    @Override
    public List<AbsenceModel> insertAll(List<AbsenceModel> t) {
        return this.absenceModelRepository.saveAllAndFlush(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());

        this.absenceModelRepository.findAll().forEach((absenceModel) -> {
            this.disciplineMigrateService.fixIfNeeded(absenceModel.getDiscipline(), absenceModel.getDepartment());
        });

    }
}
