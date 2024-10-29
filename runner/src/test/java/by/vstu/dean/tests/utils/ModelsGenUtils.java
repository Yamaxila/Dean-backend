package by.vstu.dean.tests.utils;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.dean.enums.*;
import by.vstu.dean.models.FacultyModel;
import by.vstu.dean.models.hostels.HostelRoomModel;
import by.vstu.dean.models.lessons.*;
import by.vstu.dean.models.rooms.ClassroomModel;
import by.vstu.dean.models.specs.QualificationModel;
import by.vstu.dean.models.specs.SpecialityModel;
import by.vstu.dean.models.specs.SpecializationModel;
import by.vstu.dean.models.students.GroupModel;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.models.students.internal.CitizenshipModel;
import by.vstu.dean.models.students.internal.EducationModel;
import by.vstu.dean.models.students.internal.InstitutionModel;
import by.vstu.dean.models.students.internal.StudentLanguageModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class ModelsGenUtils {

    public static <T> T genRandomObject(T input) {

        List<Field> fields = ReflectionUtils.getAllFields(input.getClass());

        fields.forEach(field -> {
            field.setAccessible(true);
            try {
                fillField(field, input);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        });


        return input;
    }

    public static void fillField(Field field, Object o) throws IllegalAccessException {

        if (field.getType().equals(Integer.class))
            field.set(o, RandomUtils.nextInt());
        else if (field.getType().equals(Long.class))
            field.set(o, RandomUtils.nextLong());
        else if (field.getType().equals(Double.class))
            field.set(o, RandomUtils.nextDouble());
        else if (field.getType().equals(Float.class))
            field.set(o, RandomUtils.nextFloat());
        else if (field.getType().equals(String.class))
            field.set(o, RandomStringUtils.randomAlphanumeric(10, 20));
        else if (field.getType().equals(Enum.class))
            field.set(o, -1);
        else if (field.getType().equals(LocalDate.class))
            field.set(o, LocalDate.now());
        else if (field.getType().equals(LocalDateTime.class))
            field.set(o, LocalDateTime.now());
        else if (field.getType().equals(Boolean.class))
            field.set(o, RandomUtils.nextInt(0, 2) == 1);

    }

    public DBBaseModel initModel(DBBaseModel model) {
        model.setStatus(EStatus.ACTIVE);
        model.setCreated(LocalDateTime.now());
        model.setUpdated(LocalDateTime.now());
        return model;
    }


    public QualificationModel qualificationModel() {
        QualificationModel qualificationModel = (QualificationModel) this.initModel(new QualificationModel());

        qualificationModel.setName("Qualification Name");
        qualificationModel.setNameGenitive("Qualification Name Genitive");

        return qualificationModel;
    }

    public SpecializationModel specializationModel(SpecialityModel specialityModel, QualificationModel qualificationModel) {
        SpecializationModel specializationModel = (SpecializationModel) this.initModel(new SpecializationModel());

        // Set some values
        specializationModel.setName(RandomStringUtils.randomAlphanumeric(10, 20));
        specializationModel.setShortName(RandomStringUtils.randomAlphanumeric(10, 20));
        specializationModel.setSpezCode(RandomStringUtils.randomAlphanumeric(10, 20));
        specializationModel.setSpec(specialityModel);
        specializationModel.setQualification(qualificationModel);

        return specializationModel;
    }

    public FacultyModel facultyModel() {
        FacultyModel facultyModel = (FacultyModel) this.initModel(new FacultyModel());

        // Set some values
        facultyModel.setName(RandomStringUtils.randomAlphanumeric(10, 20));
        facultyModel.setShortName(RandomStringUtils.randomAlphanumeric(10, 20));
        facultyModel.setRectorName(RandomStringUtils.randomAlphanumeric(10, 20));
        facultyModel.setDean(RandomStringUtils.randomAlphanumeric(10, 20));
        facultyModel.setClerkName(RandomStringUtils.randomAlphanumeric(10, 20));
        facultyModel.setEnrollMsgPaid(RandomStringUtils.randomAlphanumeric(10, 20));
        facultyModel.setEnrollDatePaid(LocalDate.now());
        facultyModel.setEnrollMsgNotPaid(RandomStringUtils.randomAlphanumeric(10, 20));
        facultyModel.setEnrollDateNotPaid(LocalDate.now());
        facultyModel.setJournalType(1);
        facultyModel.setFacultyType(1);
        facultyModel.setSemesterStartYear(2022);
        facultyModel.setSemesterEndYear(2023);
        facultyModel.setSemester(RandomStringUtils.randomAlphanumeric(10, 20));
        facultyModel.setMoveMsgNumber(RandomStringUtils.randomAlphanumeric(10, 20));
        facultyModel.setMoveMsgDate(LocalDate.now());
        facultyModel.setEducationType(1);
        return facultyModel;
    }

    public DepartmentModel departmentModel(FacultyModel facultyModel) {
        DepartmentModel departmentModel = (DepartmentModel) this.initModel(new DepartmentModel());

        // Set some values
        departmentModel.setName(RandomStringUtils.randomAlphanumeric(10, 20));
        departmentModel.setShortName(RandomStringUtils.randomAlphanumeric(10, 20));
        departmentModel.setRoom(RandomStringUtils.randomAlphanumeric(10, 20));
        departmentModel.setFaculty(facultyModel);
        return departmentModel;
    }

    public SpecialityModel specialityModel(DepartmentModel departmentModel) {
        SpecialityModel specialityModel = (SpecialityModel) this.initModel(new SpecialityModel());

        // Set some values
        specialityModel.setName("Speciality Name");
        specialityModel.setShortName("Speciality Short Name");
        specialityModel.setSpecCode("Spec Code");
        specialityModel.setDepartment(departmentModel);
        return specialityModel;
    }

    public GroupModel groupModel(FacultyModel facultyModel, SpecialityModel specialityModel) {
        GroupModel groupModel = (GroupModel) this.initModel(new GroupModel());


        // Set some values
        groupModel.setName(RandomStringUtils.randomAlphanumeric(10, 20));
        groupModel.setYearStart(2022);
        groupModel.setEndSemester(ESemester.AUTUMN);
        groupModel.setYearEnd(2023);
        groupModel.setDateStart(LocalDate.now());
        groupModel.setDateEnd(LocalDate.now());
        groupModel.setScore(85.5);
        groupModel.setSpec(specialityModel);
        groupModel.setFaculty(facultyModel);
        return groupModel;
    }

    public CitizenshipModel citizenshipModel() {
        return ModelsGenUtils.genRandomObject((CitizenshipModel)this.initModel(new CitizenshipModel()));
    }

    public EducationModel educationModel(StudentModel studentModel) {
        EducationModel educationModel = ModelsGenUtils.genRandomObject((EducationModel) this.initModel(new EducationModel()));
        educationModel.setStudent(studentModel);
        return educationModel;
    }

    public InstitutionModel institutionModel() {
        return (InstitutionModel) ModelsGenUtils.genRandomObject((InstitutionModel)this.initModel(new InstitutionModel()));
    }

    public StudentLanguageModel studentLanguageModel() {
        return (StudentLanguageModel) ModelsGenUtils.genRandomObject((StudentLanguageModel)this.initModel(new StudentLanguageModel()));
    }

    public ClassroomModel classroomModel() {
        ClassroomModel classroomModel =  (ClassroomModel) ModelsGenUtils.genRandomObject((ClassroomModel) this.initModel(new ClassroomModel()));
        classroomModel.setFrame(EFrame.values()[RandomUtils.nextInt(0, EFrame.values().length)]);
        classroomModel.setRoomType(EClassroomType.values()[RandomUtils.nextInt(0, EClassroomType.values().length)]);
        return classroomModel;
    }

    public StudentModel studentModel(GroupModel groupModel, SpecializationModel specializationModel) {

        StudentModel studentModel = (StudentModel) this.initModel(new StudentModel());

        // 
        studentModel.setLastName(RandomStringUtils.randomAlphanumeric(10, 20));
        studentModel.setFirstName(RandomStringUtils.randomAlphanumeric(10, 20));
        studentModel.setSecondName(RandomStringUtils.randomAlphanumeric(10, 20));
        studentModel.setSex(1);
//        studentModel.setCityType(1);
//        studentModel.setAddress(RandomStringUtils.randomAlphanumeric(10, 20));
//        studentModel.setAddressCountry(RandomStringUtils.randomAlphanumeric(10, 20));
//        studentModel.setAddressIndex(RandomStringUtils.randomAlphanumeric(10, 20));
//        studentModel.setAddressState(RandomStringUtils.randomAlphanumeric(10, 20));
//        studentModel.setAddressRegion(RandomStringUtils.randomAlphanumeric(10, 20));
//        studentModel.setAddressCity(RandomStringUtils.randomAlphanumeric(10, 20));
//        studentModel.setAddressStreet(RandomStringUtils.randomAlphanumeric(10, 20));
//        studentModel.setAddressHouse(RandomStringUtils.randomAlphanumeric(10, 20));
//        studentModel.setAddressHousePart(RandomStringUtils.randomAlphanumeric(10, 20));
//        studentModel.setAddressFlat(RandomStringUtils.randomAlphanumeric(10, 20));
//        studentModel.setPhone(RandomStringUtils.randomAlphanumeric(10, 20));
//        studentModel.setBenefits(RandomStringUtils.randomAlphanumeric(10, 20));
//        studentModel.setCityIsVillage(true);
//        studentModel.setLastDocument(documentModel);
//        studentModel.setLastDeviation(null);
//        studentModel.setSpecialization(specializationModel);
//        studentModel.setGroup(groupModel);
//        studentModel.setHostelRoom(null);
//        studentModel.setCheckInDate(null);
        studentModel.setEvictionDate(null);
        studentModel.setApproved(false);
        studentModel.setPhotoUrl(RandomStringUtils.randomAlphanumeric(10, 40));

        return studentModel;
    }


    public TeacherModel teacherModel(TeacherDegreeModel degreeModel) {
        TeacherModel teacherModel = (TeacherModel) this.initModel(new TeacherModel());
        teacherModel.setName(RandomStringUtils.randomAlphanumeric(10, 20));
        teacherModel.setSurname(RandomStringUtils.randomAlphanumeric(10, 20));
        teacherModel.setPatronymic(RandomStringUtils.randomAlphanumeric(10, 20));
        teacherModel.setDegree(degreeModel);
        return teacherModel;
    }

    public ExamModel examTypeModel() {
        ExamModel examModel = (ExamModel) this.initModel(new ExamModel());
        examModel.setName(RandomStringUtils.randomAlphanumeric(10, 20));
        examModel.setType(ExamType.values()[RandomUtils.nextInt(0, ExamType.values().length)]);
        return examModel;
    }

    public DisciplineModel disciplineModel() {
        DisciplineModel disciplinesModel = (DisciplineModel) this.initModel(new DisciplineModel());
        disciplinesModel.setName(RandomStringUtils.randomAlphanumeric(10, 20));
        disciplinesModel.setShortName(RandomStringUtils.randomAlphanumeric(5, 10));
        return disciplinesModel;
    }

    public TeacherDegreeModel teacherDegreeModel() {
        TeacherDegreeModel teacherDegreeModel = (TeacherDegreeModel) this.initModel(new TeacherDegreeModel());
        teacherDegreeModel.setName(RandomStringUtils.randomAlphanumeric(10, 20));
        teacherDegreeModel.setHourPrice(12.5);
        return teacherDegreeModel;
    }

    public AbsenceModel absenceModel(StudentModel studentModel, TeacherModel teacherModel, DepartmentModel departmentModel, DisciplineModel disciplineModel) {
        AbsenceModel absenceModel = (AbsenceModel) this.initModel(new AbsenceModel());
        absenceModel.setHourPrice(12.5);
        absenceModel.setAbsenceTime(12.5);
        absenceModel.setStudent(studentModel);
        absenceModel.setTeacherModel(teacherModel);
        absenceModel.setReasonMsg(RandomStringUtils.randomAlphanumeric(10, 20));
        absenceModel.setPaymentDate(LocalDate.now());
        absenceModel.setDate(LocalDate.now());
        absenceModel.setDateCompleted(LocalDate.now());
        absenceModel.setDatePrint(LocalDate.now());
        absenceModel.setDateErip(LocalDate.now());
        absenceModel.setPrinted(true);
        absenceModel.setDepartment(departmentModel);
        absenceModel.setDiscipline(disciplineModel);
        return absenceModel;
    }

    public StudyPlanModel studyPlanModel(GroupModel groupModel, TeacherModel teacherModel, DisciplineModel disciplineModel, ExamModel examModel) {

        StudyPlanModel studyPlanModel = (StudyPlanModel) this.initModel(new StudyPlanModel());
        studyPlanModel.setDiscipline(disciplineModel);
        studyPlanModel.setExam(examModel);
        studyPlanModel.setCourse(1);
        studyPlanModel.setTeacher(teacherModel);
        studyPlanModel.setGroup(groupModel);
        studyPlanModel.setSemester("1");
        studyPlanModel.setSemesterNumber(1);
        studyPlanModel.setYearEnd(2024);
        studyPlanModel.setYearStart(2023);

        return studyPlanModel;

    }

    public HostelRoomModel hostelRoomModel() {
        HostelRoomModel roomModel = (HostelRoomModel) this.initModel(new HostelRoomModel());
        roomModel.setRoomNumber(RandomUtils.nextInt(10, 20));
        roomModel.setFloor(RandomUtils.nextInt(10, 20));
        roomModel.setHostel(EHostel.values()[RandomUtils.nextInt(0, EHostel.values().length)]);
        roomModel.setRoomType(EHostelRoomType.values()[RandomUtils.nextInt(0, EHostelRoomType.values().length)]);
        return roomModel;
    }
}