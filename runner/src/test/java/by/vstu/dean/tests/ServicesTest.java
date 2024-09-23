package by.vstu.dean.tests;

import by.vstu.dean.DeanBackendApplication;
import by.vstu.dean.models.hostels.HostelRoomModel;
import by.vstu.dean.models.lessons.StudyPlanModel;
import by.vstu.dean.models.lessons.TeacherDegreeModel;
import by.vstu.dean.models.lessons.TeacherModel;
import by.vstu.dean.models.specs.QualificationModel;
import by.vstu.dean.models.specs.SpecialityModel;
import by.vstu.dean.models.specs.SpecializationModel;
import by.vstu.dean.models.students.GroupModel;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.models.students.internal.CitizenshipModel;
import by.vstu.dean.models.students.internal.EducationModel;
import by.vstu.dean.models.students.internal.InstitutionModel;
import by.vstu.dean.models.students.internal.StudentLanguageModel;
import by.vstu.dean.services.*;
import by.vstu.dean.services.students.*;
import by.vstu.dean.tests.utils.ModelsGenUtils;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes= DeanBackendApplication.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Component
public class ServicesTest {

    @Autowired
    private AbsenceService absenceService;

    @Autowired
    private CitizenshipService citizenshipService;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DisciplineService disciplineService;

    @Autowired
    private EducationService educationService;

    @Autowired
    private ExamTypeService examTypeService;

    @Autowired
    private FacultyService facultyService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private HostelRoomService hostelRoomService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private QualificationService qualificationService;

    @Autowired
    private SpecializationService specializationService;

    @Autowired
    private SpecialityService specialityService;

    @Autowired
    private StudentLanguageService studentLanguageService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudyPlanService studyPlanService;

    @Autowired
    private TeacherDegreeService teacherDegreeService;

    @Autowired
    private TeacherService teacherService;


    @Autowired
    private ModelsGenUtils modelsGenUtils;


    @Test
    @Order(0)
    void contextLoads() {
//        this.studentService.save(this.modelsGenUtils.studentModel());
        this.deleteAll();
    }

    @Test
    @Order(1)
    public void saveCitizenshipModel() {
        long countBefore = this.citizenshipService.getRepo().count();
        this.citizenshipService.save(this.modelsGenUtils.citizenshipModel());
        assertEquals(countBefore + 1, this.citizenshipService.getRepo().count());
    }

    @Test
    @Order(1)
    public void saveQualificationModel() {
        long countBefore = this.qualificationService.getRepo().count();
        this.qualificationService.save(this.modelsGenUtils.qualificationModel());
        assertEquals(countBefore + 1, this.qualificationService.getRepo().count());
    }

    @Test
    @Order(2)
    public void saveSpecialityModel() {
        long countBefore = this.specialityService.getRepo().count();
        this.specialityService.save(this.modelsGenUtils.specialityModel(this.departmentService.getRepo().findTopByOrderByIdDesc()));
        assertEquals(countBefore + 1, this.specialityService.getRepo().count());
    }

    @Test
    @Order(3)
    public void saveSpecializationModel() {
        long countBefore = this.specializationService.getRepo().count();
        this.specializationService.save(this.modelsGenUtils.specializationModel(this.specialityService.getRepo().findTopByOrderByIdDesc(), this.qualificationService.getRepo().findTopByOrderByIdDesc()));
        assertEquals(countBefore + 1, this.specializationService.getRepo().count());
    }

    @Test
    @Order(1)
    public void saveFacultyModel() {
        long countBefore = this.facultyService.getRepo().count();
        this.facultyService.save(this.modelsGenUtils.facultyModel());
        assertEquals(countBefore + 1, this.facultyService.getRepo().count());
    }

    @Test
    @Order(2)
    public void saveDisciplineModel() {
        long countBefore = this.disciplineService.getRepo().count();
        this.disciplineService.save(this.modelsGenUtils.disciplineModel());
        assertEquals(countBefore + 1, this.disciplineService.getRepo().count());
    }

    @Test
    @Order(1)
    public void saveTeacherDegreeModel() {
        long countBefore = this.teacherDegreeService.getRepo().count();
        this.teacherDegreeService.save(this.modelsGenUtils.teacherDegreeModel());
        assertEquals(countBefore + 1, this.teacherDegreeService.getRepo().count());
    }

    @Test
    @Order(3)
    public void saveDepartmentModel() {
        long countBefore = this.departmentService.getRepo().count();
        this.departmentService.save(this.modelsGenUtils.departmentModel(this.facultyService.getRepo().findTopByOrderByIdDesc()));
        assertEquals(countBefore + 1, this.departmentService.getRepo().count());
    }

    @Test
    @Order(3)
    public void saveGroupModel() {
        long countBefore = this.groupService.getRepo().count();
        this.groupService.save(this.modelsGenUtils.groupModel(this.facultyService.getRepo().findTopByOrderByIdDesc(), this.specialityService.getRepo().findTopByOrderByIdDesc()));
        assertEquals(countBefore + 1, this.groupService.getRepo().count());
    }

    @Test
    @Order(2)
    public void saveTeacherModel() {
        long countBefore = this.teacherService.getRepo().count();
        this.teacherService.save(this.modelsGenUtils.teacherModel(this.teacherDegreeService.getRepo().findTopByOrderByIdDesc()));
        assertEquals(countBefore + 1, this.teacherService.getRepo().count());
    }



    @Test
    @Order(1)
    public void saveInstitutionModel() {
        long countBefore = this.institutionService.getRepo().count();
        this.institutionService.save(this.modelsGenUtils.institutionModel());
        assertEquals(countBefore + 1, this.institutionService.getRepo().count());
    }

    @Test
    @Order(1)
    public void saveStudentLanguageModel() {
        long countBefore = this.studentLanguageService.getRepo().count();
        this.studentLanguageService.save(this.modelsGenUtils.studentLanguageModel());
        assertEquals(countBefore + 1, this.studentLanguageService.getRepo().count());
    }

//    @Test
//    @Order(2)
//    public void saveDocumentModel() {
//        long countBefore = this.documentService.getRepo().count();
//        this.documentService.save(this.modelsGenUtils.documentModel(this.studentLanguageService.getRepo().findTopByOrderByIdDesc(), this.citizenshipService.getRepo().findTopByOrderByIdDesc(), this.institutionService.getRepo().findTopByOrderByIdDesc()));
//        assertEquals(countBefore + 1, this.documentService.getRepo().count());
//    }

    @Test
    @Order(5)
    public void saveStudentModel() {
        long countBefore = this.studentService.getRepo().count();
        this.studentService.save(this.modelsGenUtils.studentModel(this.groupService.getRepo().findTopByOrderByIdDesc(), this.specializationService.getRepo().findTopByOrderByIdDesc()));
        assertEquals(countBefore + 1, this.studentService.getRepo().count());
    }

    @Test
    @Order(1)
    public void saveExamTypeModel() {
        long countBefore = this.examTypeService.getRepo().count();
        this.examTypeService.save(this.modelsGenUtils.examTypeModel());
        assertEquals(countBefore + 1, this.examTypeService.getRepo().count());}

    @Test
    @Order(6)
    public void saveEducationModel() {
        long countBefore = this.educationService.getRepo().count();
        this.educationService.save(this.modelsGenUtils.educationModel(this.studentService.getRepo().findTopByOrderByIdDesc()));
        assertEquals(countBefore + 1, this.educationService.getRepo().count());
    }

    @Test
    @Order(1)
    public void saveClassroomModel() {
        long countBefore = this.classroomService.getRepo().count();
        this.classroomService.save(this.modelsGenUtils.classroomModel());
        assertEquals(countBefore + 1, this.classroomService.getRepo().count());
    }


    @Test
    @Order(11)
    void getStudentById() {
        this.deleteAll();
        this.saveCitizenshipModel();
        this.saveStudentLanguageModel();
        this.saveSpecialityModel();
        this.saveQualificationModel();
        this.saveSpecializationModel();
        this.saveFacultyModel();
        this.saveGroupModel();

        final StudentModel studentModel = this.studentService.save(this.modelsGenUtils.studentModel(this.groupService.getRepo().findTopByOrderByIdDesc(), this.specializationService.getRepo().findTopByOrderByIdDesc()));
        final StudentModel studentModelFromService = this.studentService.getById(studentModel.getId()).orElse(null);
        assertEquals(studentModel, studentModelFromService);
    }

    @Test
    @Order(12)
    void getAllStudents() {
        this.deleteAll();
        this.saveCitizenshipModel();
        this.saveStudentLanguageModel();
        this.saveSpecialityModel();
        this.saveQualificationModel();
        this.saveSpecializationModel();
        this.saveFacultyModel();
        this.saveGroupModel();

        final List<StudentModel> studentModels = List.of(
                this.studentService.save(this.modelsGenUtils.studentModel(this.groupService.getRepo().findTopByOrderByIdDesc(), this.specializationService.getRepo().findTopByOrderByIdDesc())),
                this.studentService.save(this.modelsGenUtils.studentModel(this.groupService.getRepo().findTopByOrderByIdDesc(), this.specializationService.getRepo().findTopByOrderByIdDesc())),
                this.studentService.save(this.modelsGenUtils.studentModel(this.groupService.getRepo().findTopByOrderByIdDesc(), this.specializationService.getRepo().findTopByOrderByIdDesc()))
        );
        final List<StudentModel> studentModelsFromService = this.studentService.getAll();
        assertEquals(studentModels, studentModelsFromService);
    }

    @Test
    @Order(12)
    void getAllStudentLanguages() {
        this.deleteAll();
        final List<StudentLanguageModel> studentLanguageModels = List.of(
                this.studentLanguageService.save(this.modelsGenUtils.studentLanguageModel()),
                this.studentLanguageService.save(this.modelsGenUtils.studentLanguageModel()),
                this.studentLanguageService.save(this.modelsGenUtils.studentLanguageModel())
        );
        final List<StudentLanguageModel> studentLanguageModelsFromService = this.studentLanguageService.getAll();
        assertEquals(studentLanguageModels, studentLanguageModelsFromService);
    }


    @Test
    @Order(14)
    void getGroupById() {
        this.deleteAll();
        this.saveFacultyModel();
        this.saveQualificationModel();
        this.saveSpecializationModel();
        this.saveSpecialityModel();
        final GroupModel groupModel = this.groupService.save(this.modelsGenUtils.groupModel(this.facultyService.getRepo().findTopByOrderByIdDesc(), this.specialityService.getRepo().findTopByOrderByIdDesc()));
        final GroupModel groupModelFromService = this.groupService.getById(groupModel.getId()).orElse(null);
        assertEquals(groupModel, groupModelFromService);
    }

    @Test
    @Order(15)
    void getAllGroups() {
        this.deleteAll();
        this.saveFacultyModel();
        this.saveQualificationModel();
        this.saveSpecializationModel();
        this.saveSpecialityModel();
        final List<GroupModel> groupModels = List.of(
                this.groupService.save(this.modelsGenUtils.groupModel(this.facultyService.getRepo().findTopByOrderByIdDesc(), this.specialityService.getRepo().findTopByOrderByIdDesc())),
                this.groupService.save(this.modelsGenUtils.groupModel(this.facultyService.getRepo().findTopByOrderByIdDesc(), this.specialityService.getRepo().findTopByOrderByIdDesc())),
                this.groupService.save(this.modelsGenUtils.groupModel(this.facultyService.getRepo().findTopByOrderByIdDesc(), this.specialityService.getRepo().findTopByOrderByIdDesc()))
        );
        final List<GroupModel> groupModelsFromService = this.groupService.getAll();
        assertEquals(groupModels, groupModelsFromService);
    }


//    @Test
//    @Order(16)
//    void getDocumentById() {
//        this.deleteAll();
//        this.saveCitizenshipModel();
//        this.saveStudentLanguageModel();
//        this.saveSpecialityModel();
//        this.saveQualificationModel();
//        this.saveSpecializationModel();
//        this.saveFacultyModel();
//        this.saveGroupModel();
//        final DocumentModel documentModel = this.documentService.save(this.modelsGenUtils.documentModel(this.studentLanguageService.getRepo().findTopByOrderByIdDesc(), this.citizenshipService.getRepo().findTopByOrderByIdDesc(), this.institutionService.getRepo().findTopByOrderByIdDesc()));
//        final DocumentModel documentModelFromService = this.documentService.getById(documentModel.getId()).orElse(null);
//        assertEquals(documentModel, documentModelFromService);
//    }
//
//    @Test
//    @Order(17)
//    void getAllDocuments() {
//        this.deleteAll();
//        this.saveCitizenshipModel();
//        this.saveStudentLanguageModel();
//        this.saveSpecialityModel();
//        this.saveQualificationModel();
//        this.saveSpecializationModel();
//        this.saveFacultyModel();
//        this.saveGroupModel();
//        final List<DocumentModel> documentModels = List.of(
//                this.documentService.save(this.modelsGenUtils.documentModel(this.studentLanguageService.getRepo().findTopByOrderByIdDesc(), this.citizenshipService.getRepo().findTopByOrderByIdDesc(), this.institutionService.getRepo().findTopByOrderByIdDesc())),
//                this.documentService.save(this.modelsGenUtils.documentModel(this.studentLanguageService.getRepo().findTopByOrderByIdDesc(), this.citizenshipService.getRepo().findTopByOrderByIdDesc(), this.institutionService.getRepo().findTopByOrderByIdDesc())),
//                this.documentService.save(this.modelsGenUtils.documentModel(this.studentLanguageService.getRepo().findTopByOrderByIdDesc(), this.citizenshipService.getRepo().findTopByOrderByIdDesc(), this.institutionService.getRepo().findTopByOrderByIdDesc()))
//        );
//        final List<DocumentModel> documentModelsFromService = this.documentService.getAll();
//        assertEquals(documentModels, documentModelsFromService);
//    }

    @Test
    @Order(18)
    void getEducationById() {
        this.deleteAll();
        this.saveCitizenshipModel();
        this.saveStudentLanguageModel();
        this.saveSpecialityModel();
        this.saveQualificationModel();
        this.saveSpecializationModel();
        this.saveFacultyModel();
        this.saveGroupModel();
        this.saveStudentModel();
        final EducationModel educationModel = this.educationService.save(this.modelsGenUtils.educationModel(this.studentService.getRepo().findTopByOrderByIdDesc()));
        final EducationModel educationModelFromService = this.educationService.getById(educationModel.getId()).orElse(null);
        assertEquals(educationModel, educationModelFromService);
    }

    @Test
    @Order(19)
    void getAllEducations() {
        this.deleteAll();
        this.saveCitizenshipModel();
        this.saveStudentLanguageModel();
        this.saveSpecialityModel();
        this.saveQualificationModel();
        this.saveSpecializationModel();
        this.saveFacultyModel();
        this.saveGroupModel();
        this.saveStudentModel();
        final List<EducationModel> educationModels = List.of(
                this.educationService.save(this.modelsGenUtils.educationModel(this.studentService.getRepo().findTopByOrderByIdDesc())),
                this.educationService.save(this.modelsGenUtils.educationModel(this.studentService.getRepo().findTopByOrderByIdDesc())),
                this.educationService.save(this.modelsGenUtils.educationModel(this.studentService.getRepo().findTopByOrderByIdDesc()))
        );
        final List<EducationModel> educationModelsFromService = this.educationService.getAll();
        assertEquals(educationModels, educationModelsFromService);
    }


    @Test
    @Order(20)
    void getHostelRoomById() {
        this.deleteAll();
        final HostelRoomModel hostelRoomModel = this.hostelRoomService.save(this.modelsGenUtils.hostelRoomModel());
        final HostelRoomModel hostelRoomModelFromService = this.hostelRoomService.getById(hostelRoomModel.getId()).orElse(null);
        Assertions.assertEquals(hostelRoomModel, hostelRoomModelFromService);
    }

    @Test
    @Order(21)
    void getAllHostelRooms() {
        this.deleteAll();
        final List<HostelRoomModel> hostelRoomModels = List.of(
                this.hostelRoomService.save(this.modelsGenUtils.hostelRoomModel()),
                this.hostelRoomService.save(this.modelsGenUtils.hostelRoomModel()),
                this.hostelRoomService.save(this.modelsGenUtils.hostelRoomModel())
        );
        final List<HostelRoomModel> hostelRoomModelsFromService = this.hostelRoomService.getAll();
        Assertions.assertEquals(hostelRoomModels, hostelRoomModelsFromService);
    }

    @Test
    @Order(20)
    void getStudyPlanById() {
        this.deleteAll();
        this.saveCitizenshipModel();
        this.saveStudentLanguageModel();
        this.saveSpecialityModel();
        this.saveQualificationModel();
        this.saveSpecializationModel();
        this.saveFacultyModel();
        this.saveGroupModel();
        this.saveStudentModel();
        this.saveDepartmentModel();
        this.saveTeacherModel();
        this.saveDisciplineModel();
        this.saveExamTypeModel();
        final StudyPlanModel studyPlanModel = this.studyPlanService.save(this.modelsGenUtils.studyPlanModel(this.groupService.getRepo().findTopByOrderByIdDesc(), this.teacherService.getRepo().findTopByOrderByIdDesc(), this.disciplineService.getRepo().findTopByOrderByIdDesc(), this.examTypeService.getRepo().findTopByOrderByIdDesc()));
        final StudyPlanModel studyPlanModelFromService = this.studyPlanService.getById(studyPlanModel.getId()).orElse(null);
        Assertions.assertEquals(studyPlanModel, studyPlanModelFromService);
    }

    @Test
    @Order(21)
    void getAllStudyPlans() {
        this.deleteAll();
        this.saveCitizenshipModel();
        this.saveStudentLanguageModel();
        this.saveSpecialityModel();
        this.saveQualificationModel();
        this.saveSpecializationModel();
        this.saveFacultyModel();
        this.saveGroupModel();
        this.saveStudentModel();
        this.saveDepartmentModel();
        this.saveTeacherModel();
        this.saveDisciplineModel();
        this.saveExamTypeModel();
        final List<StudyPlanModel> studyPlanModels = List.of(
                this.studyPlanService.save(this.modelsGenUtils.studyPlanModel(this.groupService.getRepo().findTopByOrderByIdDesc(), this.teacherService.getRepo().findTopByOrderByIdDesc(), this.disciplineService.getRepo().findTopByOrderByIdDesc(), this.examTypeService.getRepo().findTopByOrderByIdDesc())),
                this.studyPlanService.save(this.modelsGenUtils.studyPlanModel(this.groupService.getRepo().findTopByOrderByIdDesc(), this.teacherService.getRepo().findTopByOrderByIdDesc(), this.disciplineService.getRepo().findTopByOrderByIdDesc(), this.examTypeService.getRepo().findTopByOrderByIdDesc())),
                this.studyPlanService.save(this.modelsGenUtils.studyPlanModel(this.groupService.getRepo().findTopByOrderByIdDesc(), this.teacherService.getRepo().findTopByOrderByIdDesc(), this.disciplineService.getRepo().findTopByOrderByIdDesc(), this.examTypeService.getRepo().findTopByOrderByIdDesc()))
        );
        final List<StudyPlanModel> studyPlanModelsFromService = this.studyPlanService.getAll();
        Assertions.assertEquals(studyPlanModels, studyPlanModelsFromService);
    }

    @Test
    @Order(22)
    void getInstitutionById() {
        this.deleteAll();
        final InstitutionModel institutionModel = this.institutionService.save(this.modelsGenUtils.institutionModel());
        final InstitutionModel institutionModelFromService = this.institutionService.getById(institutionModel.getId()).orElse(null);
        assertEquals(institutionModel, institutionModelFromService);
    }

    @Test
    @Order(23)
    void getAllInstitutions() {
        this.deleteAll();
        final List<InstitutionModel> institutionModels = List.of(
                this.institutionService.save(this.modelsGenUtils.institutionModel()),
                this.institutionService.save(this.modelsGenUtils.institutionModel()),
                this.institutionService.save(this.modelsGenUtils.institutionModel())
        );
        final List<InstitutionModel> institutionModelsFromService = this.institutionService.getAll();
        assertEquals(institutionModels, institutionModelsFromService);
    }

    @Test
    @Order(24)
    void getQualificationById() {
        this.deleteAll();
        final QualificationModel qualificationModel = this.qualificationService.save(this.modelsGenUtils.qualificationModel());
        final QualificationModel qualificationModelFromService = this.qualificationService.getById(qualificationModel.getId()).orElse(null);
        assertEquals(qualificationModel, qualificationModelFromService);
    }

    @Test
    @Order(25)
    void getAllQualifications() {
        this.deleteAll();
        final List<QualificationModel> qualificationModels = List.of(
                this.qualificationService.save(this.modelsGenUtils.qualificationModel()),
                this.qualificationService.save(this.modelsGenUtils.qualificationModel()),
                this.qualificationService.save(this.modelsGenUtils.qualificationModel())
        );
        final List<QualificationModel> qualificationModelsFromService = this.qualificationService.getAll();
        assertEquals(qualificationModels, qualificationModelsFromService);
    }

    @Test
    @Order(26)
    void getSpecializationById() {
        this.deleteAll();
        this.saveQualificationModel();
        this.saveDepartmentModel();
        this.saveSpecialityModel();
        final SpecializationModel specializationModel = this.specializationService.save(this.modelsGenUtils.specializationModel(this.specialityService.getRepo().findTopByOrderByIdDesc(), this.qualificationService.getRepo().findTopByOrderByIdDesc()));
        final SpecializationModel specializationModelFromService = this.specializationService.getById(specializationModel.getId()).orElse(null);
        assertEquals(specializationModel, specializationModelFromService);
    }

    @Test
    @Order(27)
    void getAllSpecializations() {
        this.deleteAll();
        this.saveQualificationModel();
        this.saveDepartmentModel();
        this.saveSpecialityModel();
        final List<SpecializationModel> specializationModels = List.of(
                this.specializationService.save(this.modelsGenUtils.specializationModel(this.specialityService.getRepo().findTopByOrderByIdDesc(), this.qualificationService.getRepo().findTopByOrderByIdDesc())),
                this.specializationService.save(this.modelsGenUtils.specializationModel(this.specialityService.getRepo().findTopByOrderByIdDesc(), this.qualificationService.getRepo().findTopByOrderByIdDesc())),
                this.specializationService.save(this.modelsGenUtils.specializationModel(this.specialityService.getRepo().findTopByOrderByIdDesc(), this.qualificationService.getRepo().findTopByOrderByIdDesc()))
        );
        final List<SpecializationModel> specializationModelsFromService = this.specializationService.getAll();
        assertEquals(specializationModels, specializationModelsFromService);
    }

    @Test
    @Order(28)
    void getSpecialityById() {
        this.deleteAll();
        this.saveDepartmentModel();
        final SpecialityModel specialityModel = this.specialityService.save(this.modelsGenUtils.specialityModel(this.departmentService.getRepo().findTopByOrderByIdDesc()));
        final SpecialityModel specialityModelFromService = this.specialityService.getById(specialityModel.getId()).orElse(null);
        assertEquals(specialityModel, specialityModelFromService);
    }

    @Test
    @Order(29)
    void getAllSpecialities() {
        this.deleteAll();
        this.saveDepartmentModel();
        final List<SpecialityModel> specialityModels = List.of(
                this.specialityService.save(this.modelsGenUtils.specialityModel(this.departmentService.getRepo().findTopByOrderByIdDesc())),
                this.specialityService.save(this.modelsGenUtils.specialityModel(this.departmentService.getRepo().findTopByOrderByIdDesc())),
                this.specialityService.save(this.modelsGenUtils.specialityModel(this.departmentService.getRepo().findTopByOrderByIdDesc()))
        );
        final List<SpecialityModel> specialityModelsFromService = this.specialityService.getAll();
        assertEquals(specialityModels, specialityModelsFromService);
    }
    @Test
    @Order(30)
    void getAllTeacherDegrees() {
        this.deleteAll();
        final List<TeacherDegreeModel> teacherDegreeModels = List.of(
                this.teacherDegreeService.save(this.modelsGenUtils.teacherDegreeModel()),
                this.teacherDegreeService.save(this.modelsGenUtils.teacherDegreeModel()),
                this.teacherDegreeService.save(this.modelsGenUtils.teacherDegreeModel())
        );
        final List<TeacherDegreeModel> teacherDegreeModelsFromService = this.teacherDegreeService.getAll();
        assertEquals(teacherDegreeModels, teacherDegreeModelsFromService);
    }

    @Test
    @Order(31)
    void getTeacherDegreeById() {
        this.deleteAll();
        final TeacherDegreeModel teacherDegreeModel = this.teacherDegreeService.save(this.modelsGenUtils.teacherDegreeModel());
        final TeacherDegreeModel teacherDegreeModelFromService = this.teacherDegreeService.getById(teacherDegreeModel.getId()).orElse(null);
        assertEquals(teacherDegreeModel, teacherDegreeModelFromService);
    }

    @Test
    @Order(34)
    void getAllCitizenship() {
        this.deleteAll();
        final List<CitizenshipModel> citizenshipModels = List.of(
                this.citizenshipService.save(this.modelsGenUtils.citizenshipModel()),
                this.citizenshipService.save(this.modelsGenUtils.citizenshipModel()),
                this.citizenshipService.save(this.modelsGenUtils.citizenshipModel())
        );
        final List<CitizenshipModel> citizenshipModelsFromService = this.citizenshipService.getAll();
        assertEquals(citizenshipModels, citizenshipModelsFromService);
    }

    @Test
    @Order(35)
    void getCitizenshipById() {
        this.deleteAll();
        final CitizenshipModel citizenshipModel = this.citizenshipService.save(this.modelsGenUtils.citizenshipModel());
        final CitizenshipModel citizenshipModelFromService = this.citizenshipService.getById(citizenshipModel.getId()).orElse(null);
        assertEquals(citizenshipModel, citizenshipModelFromService);
    }

    @Test
    @Order(20)
    void getTeacherById() {
        // Delete all data
        this.deleteAll();

        // Save required models
        this.saveTeacherDegreeModel();

        // Get teacher model by ID
        final TeacherModel teacherModel = this.teacherService.save(this.modelsGenUtils.teacherModel(this.teacherDegreeService.getRepo().findTopByOrderByIdDesc()));
        final TeacherModel teacherModelFromService = this.teacherService.getById(teacherModel.getId()).orElse(null);

        // Assert that the retrieved teacher model is equal to the saved one
        assertEquals(teacherModel, teacherModelFromService);
    }

    @Test
    @Order(21)
    void getAllTeachers() {
        // Delete all data
        this.deleteAll();

        // Save required models
        this.saveTeacherDegreeModel();

        // Get all teacher models
        final List<TeacherModel> teacherModels = List.of(
                this.teacherService.save(this.modelsGenUtils.teacherModel(this.teacherDegreeService.getRepo().findTopByOrderByIdDesc())),
                this.teacherService.save(this.modelsGenUtils.teacherModel(this.teacherDegreeService.getRepo().findTopByOrderByIdDesc())),
                this.teacherService.save(this.modelsGenUtils.teacherModel(this.teacherDegreeService.getRepo().findTopByOrderByIdDesc()))
        );
        final List<TeacherModel> teacherModelsFromService = this.teacherService.getAll();

        // Assert that the retrieved teacher models are equal to the saved ones
        assertEquals(teacherModels, teacherModelsFromService);
    }



    @Test
    @Order(50)
    public void deleteAll() {
        this.absenceService.getRepo().deleteAllInBatch();
        this.studyPlanService.getRepo().deleteAllInBatch();
        this.educationService.getRepo().deleteAllInBatch();
        this.studentService.getRepo().deleteAllInBatch();
        this.studentLanguageService.getRepo().deleteAllInBatch();
        this.groupService.getRepo().deleteAllInBatch();
        this.specializationService.getRepo().deleteAllInBatch();
        this.specialityService.getRepo().deleteAllInBatch();
        this.departmentService.getRepo().deleteAllInBatch();
        this.facultyService.getRepo().deleteAllInBatch();
        this.classroomService.getRepo().deleteAllInBatch();
        this.qualificationService.getRepo().deleteAllInBatch();
        this.teacherService.getRepo().deleteAllInBatch();
        this.teacherDegreeService.getRepo().deleteAllInBatch();
        this.citizenshipService.getRepo().deleteAllInBatch();
        this.disciplineService.getRepo().deleteAllInBatch();
        this.examTypeService.getRepo().deleteAllInBatch();
        this.institutionService.getRepo().deleteAllInBatch();
        this.hostelRoomService.getRepo().deleteAllInBatch();
    }

}
