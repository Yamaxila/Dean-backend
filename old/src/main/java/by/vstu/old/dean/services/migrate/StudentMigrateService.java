package by.vstu.old.dean.services.migrate;


import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.core.utils.StringUtils;
import by.vstu.dean.enums.EMobileOperatorType;
import by.vstu.dean.enums.EPassportType;
import by.vstu.dean.enums.EPaymentType;
import by.vstu.dean.models.specs.SpecializationModel;
import by.vstu.dean.models.students.GroupModel;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.models.students.internal.*;
import by.vstu.dean.repo.ParentModelRepository;
import by.vstu.dean.repo.SpecializationModelRepository;
import by.vstu.dean.services.GroupService;
import by.vstu.dean.services.SpecializationService;
import by.vstu.dean.services.students.*;
import by.vstu.old.dean.models.DStudentModel;
import by.vstu.old.dean.repo.DStudentModelRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentMigrateService extends BaseMigrateService<StudentModel, DStudentModel> {

    private final SpecializationModelRepository specializationModelRepository;

    private final CitizenshipService citizenshipService;
    private final InstitutionService institutionService;
    private final GroupService groupService;
    private final StudentService studentService;
    private final EducationService educationService;
    private final StudentLanguageService studentLanguageService;
    private final SpecializationService specializationService;

    private final ParentModelRepository parentModelRepository;

    private final DStudentModelRepository dStudentModelRepository;


    private final List<SpecializationModel> specializations = new ArrayList<>();
    private final List<GroupModel> groups = new ArrayList<>();
    private final ModelMapper modelMapper;

    @Override
    public Long getLastDBId() {
        return this.studentService.getRepo().findTopByOrderByIdDesc() == null ? 0 : this.studentService.getRepo().findTopByOrderByIdDesc().getId();
    }

    @Override
    public List<StudentModel> convertNotExistsFromDB() {

        List<Long> ids = this.studentService.getRepo().findAllSourceIds();
//        this.specializations.addAll(this.specializationModelRepository.findAll());
//        this.groups.addAll(this.groupModelRepository.findAll());

        List<StudentModel> out = this.groupService.getAll().parallelStream()
                .map(m -> this.convertList(this.dStudentModelRepository.findAllByGroupId(m.getSourceId())
                        .stream().filter(p -> !ids.contains(p.getId())).toList())).flatMap(Collection::stream).toList();

        this.groups.clear();
        this.specializations.clear();

        return out.stream().collect(Collectors.toMap(
                StudentModel::getSourceId,
                studentModel -> studentModel,
                (s1, s2) -> s2.getId() > s1.getId() ? s2 : s1
        )).values().stream().toList();
    }

    @Override
    public StudentModel convertSingle(DStudentModel dStudentModel, boolean update) {

        StudentModel studentModel = new StudentModel();

        EStatus status = dStudentModel.isExpelled() || (dStudentModel.getGroup().getCurrentCourse() != null && dStudentModel.getGroup().getCurrentCourse().equals(99)) ? EStatus.DELETED : EStatus.ACTIVE;

//        if (this.groups.isEmpty()) {
//            this.groups.addAll(this.groupModelRepository.findAll());
//        }
//
//        if (this.specializations.isEmpty()) {
//            this.specializations.addAll(this.specializationModelRepository.findAll());
//        }

        GroupModel group = this.groupService.getBySourceId(dStudentModel.getGroup().getId());
        CitizenshipModel citizenship = this.citizenshipService.getBySourceId(Long.valueOf(dStudentModel.getCitizenship()));
        StudentLanguageModel studentLanguage = this.studentLanguageService.getBySourceId(Long.valueOf(dStudentModel.getStudentLanguage()));
        InstitutionModel institutionModel = this.institutionService.getBySourceId(dStudentModel.getInstitution().getId());
        SpecializationModel specialization = this.specializationService.getBySourceId(dStudentModel.getSpecialization().getId());

        if (group == null)
            throw new RuntimeException("Group for student with sourceId " + dStudentModel.getId() + " not found!");

        if (citizenship == null)
            throw new RuntimeException("Citizenship for student with sourceId " + dStudentModel.getId() + " not found!");

        if (studentLanguage == null)
            throw new RuntimeException("StudentLanguage for student with sourceId " + dStudentModel.getId() + " not found!");

        if (institutionModel == null)
            throw new RuntimeException("Institution for student with sourceId " + dStudentModel.getId() + " not found!");

//        if (specialization == null && dStudentModel.getSpecialization() != null)
//            throw new RuntimeException("Specialization for student with sourceId " + dStudentModel.getId() + " not found!");

        studentModel.setSpecialization(specialization);
        studentModel.setStudentLanguage(studentLanguage);
        studentModel.setCitizenship(citizenship);
        studentModel.setInstitution(institutionModel);
        studentModel.setGroup(group);
        studentModel.setSurname(StringUtils.safeTrim(dStudentModel.getLastName()));
        studentModel.setName(StringUtils.safeTrim(dStudentModel.getFirstName()));
        studentModel.setPatronymic(StringUtils.safeTrim(dStudentModel.getSecondName()));
        studentModel.setBirthDate(dStudentModel.getBirthDate() != null ? dStudentModel.getBirthDate().toLocalDate() : LocalDate.now());
        studentModel.setBirthPlace(StringUtils.safeTrim(dStudentModel.getBirthPlace()));
        studentModel.setCaseNo(Long.parseLong(StringUtils.safeTrim(dStudentModel.getCaseNo()).replaceAll("[^0-9]", "")));
        studentModel.setDocumentNumber(StringUtils.canBeInt(dStudentModel.getDocumentNumber()) ? Long.parseLong(StringUtils.safeTrim(dStudentModel.getDocumentNumber()).replaceAll("[^0-9]", "")) : -1L);
        studentModel.setEnrollDate(dStudentModel.getEnrollmentDate() != null ? dStudentModel.getEnrollmentDate().toLocalDate() : LocalDate.now());
        studentModel.setNeedHostel(!(dStudentModel.getHostel() == null ||
                dStudentModel.getHostel().isEmpty() ||
                dStudentModel.getHostel().isBlank() ||
                dStudentModel.getHostel().equals("0")));
        studentModel.setEducationString(StringUtils.safeTrim(dStudentModel.getEducationString()));
        studentModel.setEducationYearEnd(dStudentModel.getEducationYearEnd());
        studentModel.setEmail(dStudentModel.getEmail());
        studentModel.setMove(dStudentModel.getMove() == 1);
        studentModel.setStateSupport(dStudentModel.getStateSupport() == 1);
        //TODO: нужно правильно парсить телефон
        PhoneModel phoneModel = PhoneModel.builder()
                .type(EMobileOperatorType.UNKNOWN)
                .phone(StringUtils.safeTrim(dStudentModel.getPhone())
                ).build();

        if (!update) {
            phoneModel.setCreated(LocalDateTime.now());
            phoneModel.setSourceId(dStudentModel.getId());
        }
        phoneModel.setUpdated(LocalDateTime.now());
        phoneModel.setStatus(status);
        studentModel.setPhone(phoneModel);

        studentModel.setJob(StringUtils.safeTrim(dStudentModel.getJob()));
        studentModel.setJobExperience(dStudentModel.getJobExperience());

        studentModel.setUnbound(StringUtils.safeTrim(dStudentModel.getUnbound()));

        studentModel.setBenefits(StringUtils.safeTrim(dStudentModel.getBenefits()   ));
        studentModel.setSex((dStudentModel.getSex() != null && dStudentModel.getSex().equals("М")) ? 1 : 0);
        studentModel.setSourceId(dStudentModel.getId());
        studentModel.setFullNameL(StringUtils.safeTrim(dStudentModel.getFullNameL()));
        studentModel.setFirstNameL(StringUtils.safeTrim(dStudentModel.getFirstNameL()));

        PassportModel passportModel = PassportModel.builder()
                .passportId(StringUtils.safeTrim(dStudentModel.getPassportId()))
                .passportIssueDate(dStudentModel.getPassportIssueDate() != null ? dStudentModel.getPassportIssueDate().toLocalDate() : LocalDate.now())
                .passportIssueString(StringUtils.safeTrim(dStudentModel.getPassportIssueString()))
                .passportNumber(StringUtils.safeTrim(dStudentModel.getPassportNumber()))
                .passportSerial(StringUtils.safeTrim(dStudentModel.getPassportSerial()))
                .passportType(EPassportType.PASSPORT) // У нас нет типов паспортов в старом деканате. потом можно будет по номеру паспорта попробовать парсить
                .build();

        if (!update) {
            passportModel.setCreated(LocalDateTime.now());
            passportModel.setSourceId(dStudentModel.getId());
        }

        passportModel.setUpdated(LocalDateTime.now());
        passportModel.setStatus(status);
        studentModel.setPassport(passportModel);

        AddressModel regAddress = AddressModel.builder() // по факту такой же бред, т.к. в базе нет инфы на многих студентов.
                .country(StringUtils.safeTrim(dStudentModel.getRegCity()))
                .state(StringUtils.safeTrim(dStudentModel.getRegState()))
                .region(StringUtils.safeTrim(dStudentModel.getRegRegion()))
                .city(StringUtils.safeTrim(dStudentModel.getRegCity2()))
                .street(StringUtils.safeTrim(dStudentModel.getRegStreet()))
                .house(StringUtils.safeTrim(dStudentModel.getRegHouse()))
                .housePart(StringUtils.safeTrim(dStudentModel.getRegHousePart()))
                .flat(StringUtils.safeTrim(dStudentModel.getRegFlat()))
                .postIndex(StringUtils.safeTrim(dStudentModel.getRegIndex()))
                .build();
        if (!update) {
            regAddress.setCreated(LocalDateTime.now());
            regAddress.setSourceId(dStudentModel.getId());
        }

        regAddress.setUpdated(LocalDateTime.now());
        regAddress.setStatus(status);
        studentModel.setRegAddress(regAddress);

        AddressModel address = AddressModel.builder()
                .country(StringUtils.safeTrim(dStudentModel.getAddressCity()))
                .state(StringUtils.safeTrim(dStudentModel.getAddressState()))
                .region(StringUtils.safeTrim(dStudentModel.getAddressRegion()))
                .city(StringUtils.safeTrim(dStudentModel.getAddressCity2())) // отвратительный нейминг в старой базе
                .street(StringUtils.safeTrim(dStudentModel.getAddressStreet()))
                .house(StringUtils.safeTrim(dStudentModel.getAddressHouse()))
                .housePart(StringUtils.safeTrim(dStudentModel.getAddressHousePart()))
                .flat(StringUtils.safeTrim(dStudentModel.getAddressFlat()))
                .postIndex(StringUtils.safeTrim(dStudentModel.getAddressIndex()))
                .build();


        if (StringUtils.safeTrim(dStudentModel.getPaymentType()).equalsIgnoreCase("бюджет"))
            studentModel.setPaymentType(EPaymentType.NOT_PAID);
        else if (StringUtils.safeTrim(dStudentModel.getPaymentType()).equalsIgnoreCase("платное"))
            studentModel.setPaymentType(EPaymentType.PAID);
        else
            studentModel.setPaymentType(EPaymentType.UNKNOWN);

        if (!update) {
            address.setCreated(LocalDateTime.now());
            address.setSourceId(dStudentModel.getId());
        }

        address.setUpdated(LocalDateTime.now());
        address.setStatus(status);
        studentModel.setAddress(address);

        if (studentModel.getRegAddress().getCity().isEmpty() && studentModel.getRegAddress().getHouse().isEmpty())
            studentModel.setRegAddress(studentModel.getAddress()); // костыль, но тут только такой выход я вижу

        studentModel.setApproved(false);
        studentModel.setHostelRoom(null);

        studentModel.setStatus(status);
        if (dStudentModel.getSpecialization() != null)
            studentModel.setSpecialization(this.specializationService.getBySourceId(dStudentModel.getSpecialization().getId()));

        if (!update) {
            studentModel.setCreated(LocalDateTime.now());
        }

        studentModel.setUpdated(LocalDateTime.now());

        studentModel.setPhotoUrl("/api/v1/files/students/download?filename=none.jpg");

        return studentModel;
    }

    public List<ParentModel> createParentsForStudent(DStudentModel dStudentModel, StudentModel studentModel) {
        if (dStudentModel == null)
            return new ArrayList<>();

        List<ParentModel> parents = new ArrayList<>();

        if (dStudentModel.getFatherFullName() != null && !dStudentModel.getFatherFullName().isEmpty() && dStudentModel.getFatherFullName().split(" ").length == 3) {
            parents.add(ParentModel.builder()
                    .student(studentModel)
                    .surname(dStudentModel.getFatherFullName().split(" ")[0])
                    .name(dStudentModel.getFatherFullName().split(" ")[1])
                    .patronymic(dStudentModel.getFatherFullName().split(" ")[2])
                    .job(StringUtils.safeTrim(dStudentModel.getFatherJob()))
                    .phone(PhoneModel.builder()
                            .phone(StringUtils.safeTrim(dStudentModel.getFatherPhone()))
                            .type(EMobileOperatorType.UNKNOWN).build()
                    ).build()
            );
        }

        if (dStudentModel.getMotherFullName() != null && !dStudentModel.getMotherFullName().isEmpty() && dStudentModel.getMotherFullName().split(" ").length == 3) {
            parents.add(ParentModel.builder()
                    .student(studentModel)
                    .surname(dStudentModel.getMotherFullName().split(" ")[0])
                    .name(dStudentModel.getMotherFullName().split(" ")[1])
                    .patronymic(dStudentModel.getMotherFullName().split(" ")[2])
                    .job(StringUtils.safeTrim(dStudentModel.getMotherJob()))
                    .phone(PhoneModel.builder()
                            .phone(StringUtils.safeTrim(dStudentModel.getMotherPhone()))
                            .type(EMobileOperatorType.UNKNOWN).build()
                    ).build()
            );
        }


        return parents.stream().peek(m -> {
            m.getPhone().setCreated(LocalDateTime.now());
            m.getPhone().setUpdated(LocalDateTime.now());
            m.getPhone().setSourceId(dStudentModel.getId());
            m.getPhone().setStatus(studentModel.getStatus());

            m.setCreated(LocalDateTime.now());
            m.setUpdated(LocalDateTime.now());
            m.setSourceId(dStudentModel.getId());
            m.setStatus(studentModel.getStatus());
        }).toList();
    }

    public List<EducationModel> createEducationsForStudent(DStudentModel dStudentModel, StudentModel studentModel) {
        if (dStudentModel == null)
            return new ArrayList<>();

        List<EducationModel> educations = new ArrayList<>();

        if (!StringUtils.safeTrim(dStudentModel.getEducation1()).isEmpty())
            this.addEducation(educations, studentModel, StringUtils.safeTrim(dStudentModel.getEducation1()), StringUtils.safeTrim(dStudentModel.getEducation1DocumentType()), StringUtils.safeTrim(dStudentModel.getEducation1DocumentSerial()), StringUtils.safeTrim(dStudentModel.getEducation1DocumentNumber()));
        if (!StringUtils.safeTrim(dStudentModel.getEducation2()).isEmpty())
            this.addEducation(educations, studentModel, StringUtils.safeTrim(dStudentModel.getEducation2()), StringUtils.safeTrim(dStudentModel.getEducation2DocumentType()), StringUtils.safeTrim(dStudentModel.getEducation2DocumentSerial()), StringUtils.safeTrim(dStudentModel.getEducation2DocumentNumber()));
        if (!StringUtils.safeTrim(dStudentModel.getEducation3()).isEmpty())
            this.addEducation(educations, studentModel, StringUtils.safeTrim(dStudentModel.getEducation3()), StringUtils.safeTrim(dStudentModel.getEducation3DocumentType()), StringUtils.safeTrim(dStudentModel.getEducation3DocumentSerial()), StringUtils.safeTrim(dStudentModel.getEducation3DocumentNumber()));

        return educations;
    }

    private void addEducation(List<EducationModel> toAdd, StudentModel studentModel, String education, String documentType, String serial, String number) {
        toAdd.add(EducationModel.builder()
                .student(studentModel)
                .education(education)
                .educationDocumentType(documentType)
                .educationDocumentSerial(serial)
                .educationDocumentNumber(number)
                .build());
    }

    @Override
    public List<StudentModel> convertList(List<DStudentModel> t) {

        List<StudentModel> temp = new ArrayList<>();

        t.forEach(dStudentModel -> temp.add(this.convertSingle(dStudentModel)));

        return temp;
    }

    @Override
    public StudentModel insertSingle(StudentModel t) {
        return this.studentService.save(t);
    }

    @Override
    public List<StudentModel> insertAll(List<StudentModel> t) {
        return this.studentService.saveAll(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        // this.insertAll(this.convertNotExistsFromDB());
        List<Long> educationSIds = this.educationService.getAll().stream().map(EducationModel::getStudent).map(DBBaseModel::getId).toList();
        List<Long> parentsSIds = this.parentModelRepository.findAll().stream().map(ParentModel::getStudent).map(DBBaseModel::getId).toList();
        List<StudentModel> studentModels = this.studentService.getAll().stream().filter(p -> !educationSIds.contains(p.getId()) && parentsSIds.contains(p.getId())).toList();

//        this.educationService.saveAll(studentModels.stream().flatMap(m -> this.createEducationsForStudent(this.dStudentModelRepository.findById(m.getSourceId()).orElse(null), m).stream()).toList());
//        this.parentModelRepository.saveAll(studentModels.stream().flatMap(m -> this.createParentsForStudent(this.dStudentModelRepository.findById(m.getSourceId()).orElse(null), m).stream()).toList());
    }
}
