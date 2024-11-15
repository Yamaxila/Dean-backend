package by.vstu.migrate.v1.service.migrate;

import by.vstu.dean.core.utils.StringUtils;
import by.vstu.dean.enums.EMobileOperatorType;
import by.vstu.dean.enums.EPassportType;
import by.vstu.dean.enums.EPaymentType;
import by.vstu.dean.models.students.StudentModel;
import by.vstu.dean.models.students.internal.*;
import by.vstu.dean.services.GroupService;
import by.vstu.dean.services.HostelRoomService;
import by.vstu.dean.services.SpecializationService;
import by.vstu.dean.services.students.CitizenshipService;
import by.vstu.dean.services.students.InstitutionService;
import by.vstu.dean.services.students.StudentLanguageService;
import by.vstu.dean.services.students.StudentService;
import by.vstu.migrate.v1.models.students.V1StudentModel;
import by.vstu.migrate.v1.repo.V1StudentModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentMigrateService extends BaseMigrateService<StudentModel, V1StudentModel> {

    private final StudentService studentService;
    private final V1StudentModelRepository v1StudentModelRepository;

    private final InstitutionService institutionService;
    private final CitizenshipService citizenshipService;
    private final StudentLanguageService studentLanguageService;
    private final SpecializationService specializationService;
    private final GroupService groupService;
    private final HostelRoomService hostelRoomService;

    @Override
    public List<StudentModel> convertNotExistsFromDB() {
        return this.convertList(this.v1StudentModelRepository.findAll());
    }

    @Override
    public StudentModel convertSingle(V1StudentModel v1StudentModel, boolean update) {
        StudentModel s = new StudentModel();
        s.setId(v1StudentModel.getId());
        s.setSourceId(v1StudentModel.getSourceId());
        s.setStatus(v1StudentModel.getStatus().map());
        s.setCreated(v1StudentModel.getCreated());
        s.setUpdated(v1StudentModel.getUpdated());

        s.setLastName(v1StudentModel.getLastName());
        s.setFirstName(v1StudentModel.getFirstName());
        s.setSecondName(v1StudentModel.getSecondName());

        if (StringUtils.safeTrim(v1StudentModel.getLastDocument().getPaymentType()).equalsIgnoreCase("бюджет"))
            s.setPaymentType(EPaymentType.NOT_PAID);
        else if (StringUtils.safeTrim(v1StudentModel.getLastDocument().getPaymentType()).equalsIgnoreCase("платное"))
            s.setPaymentType(EPaymentType.PAID);
        else
            s.setPaymentType(EPaymentType.UNKNOWN);

        s.setSex(v1StudentModel.getSex());

        PhoneModel phoneModel = PhoneModel.builder()
                .type(EMobileOperatorType.UNKNOWN)
                .phone(StringUtils.safeTrim(v1StudentModel.getPhone())
                ).build();
        if (!update) {
            phoneModel.setCreated(LocalDateTime.now());
            phoneModel.setSourceId(v1StudentModel.getSourceId());
        }
        phoneModel.setUpdated(LocalDateTime.now());
        phoneModel.setStatus(v1StudentModel.getStatus().map());
        s.setPhone(phoneModel);

        s.setEmail(v1StudentModel.getLastDocument().getEmail());
        s.setBirthDate(v1StudentModel.getBirthDate());
        s.setEducationString(v1StudentModel.getLastDocument().getEducationString());

        this.institutionService.getById(v1StudentModel.getLastDocument().getInstitution().getId()).ifPresent(s::setInstitution);

        if (s.getInstitution() == null) {
            throw new RuntimeException("Institution for student with id = %d not found".formatted(v1StudentModel.getId()));
        }

        s.setEducationYearEnd(v1StudentModel.getLastDocument().getEducationYearEnd());
        s.setJob(v1StudentModel.getLastDocument().getJob());
        s.setJobExperience(v1StudentModel.getLastDocument().getJobExperience());
        s.setEnrollmentDate(v1StudentModel.getLastDocument().getEnrollmentDate());
        s.setReEnroll(v1StudentModel.getLastDocument().isReEnroll());
        s.setUnbound(v1StudentModel.getLastDocument().getUnbound());
        s.setStateSupport(v1StudentModel.getLastDocument().isStateSupport());
        s.setMove(v1StudentModel.getLastDocument().isMove());
        s.setEnrollDate(v1StudentModel.getLastDocument().getEnrollDate());
        s.setFullNameL(v1StudentModel.getLastDocument().getFullNameL());
        s.setFirstNameL(v1StudentModel.getLastDocument().getFirstNameL());
        s.setMaidenName(null);
        s.setCaseNo(v1StudentModel.getLastDocument().getCaseNo());
        s.setDocumentNumber(v1StudentModel.getLastDocument().getDocumentNumber());

        AddressModel regAddress = AddressModel.builder()
                .country(StringUtils.safeTrim(v1StudentModel.getLastDocument().getRegCity()))
                .state(StringUtils.safeTrim(v1StudentModel.getLastDocument().getRegState()))
                .region(StringUtils.safeTrim(v1StudentModel.getLastDocument().getRegRegion()))
                .city(StringUtils.safeTrim(v1StudentModel.getLastDocument().getRegCity2()))
                .street(StringUtils.safeTrim(v1StudentModel.getLastDocument().getRegStreet()))
                .house(StringUtils.safeTrim(v1StudentModel.getLastDocument().getRegHouse()))
                .housePart(StringUtils.safeTrim(v1StudentModel.getLastDocument().getRegHousePart()))
                .flat(StringUtils.safeTrim(v1StudentModel.getLastDocument().getRegFlat()))
                .postIndex(StringUtils.safeTrim(v1StudentModel.getLastDocument().getRegIndex()))
                .build();
        if (!update) {
            regAddress.setCreated(LocalDateTime.now());
            regAddress.setSourceId(v1StudentModel.getSourceId());
        }

        regAddress.setUpdated(LocalDateTime.now());
        regAddress.setStatus(v1StudentModel.getStatus().map());
        s.setRegAddress(regAddress);

        AddressModel address = AddressModel.builder()
                .country(StringUtils.safeTrim(v1StudentModel.getAddressCountry()))
                .state(StringUtils.safeTrim(v1StudentModel.getAddressState()))
                .region(StringUtils.safeTrim(v1StudentModel.getAddressRegion()))
                .city(StringUtils.safeTrim(v1StudentModel.getAddressCity()))
                .street(StringUtils.safeTrim(v1StudentModel.getAddressStreet()))
                .house(StringUtils.safeTrim(v1StudentModel.getAddressHouse()))
                .housePart(StringUtils.safeTrim(v1StudentModel.getAddressHousePart()))
                .flat(StringUtils.safeTrim(v1StudentModel.getAddressFlat()))
                .postIndex(StringUtils.safeTrim(v1StudentModel.getAddressIndex()))
                .build();
        if (!update) {
            address.setCreated(LocalDateTime.now());
            address.setSourceId(v1StudentModel.getSourceId());
        }

        address.setUpdated(LocalDateTime.now());
        address.setStatus(v1StudentModel.getStatus().map());
        s.setAddress(address);

        if (s.getRegAddress().getCity().isEmpty() && s.getRegAddress().getHouse().isEmpty())
            s.setRegAddress(s.getAddress());

        this.citizenshipService.getById(v1StudentModel.getLastDocument().getCitizenship().getId()).ifPresent(s::setCitizenship);

        if (s.getCitizenship() == null) {
            throw new RuntimeException("Citizenship for student with id = %d not found".formatted(v1StudentModel.getId()));
        }

        this.studentLanguageService.getById(v1StudentModel.getLastDocument().getStudentLanguage().getId()).ifPresent(s::setStudentLanguage);

        if (s.getStudentLanguage() == null) {
            throw new RuntimeException("StudentLanguage for student with id = %d not found".formatted(v1StudentModel.getId()));
        }

        PassportModel passportModel = PassportModel.builder()
                .passportId(StringUtils.safeTrim(v1StudentModel.getLastDocument().getPassportId()))
                .passportIssueDate(v1StudentModel.getLastDocument().getPassportIssueDate() != null ? v1StudentModel.getLastDocument().getPassportIssueDate() : LocalDate.now())
                .passportIssueString(StringUtils.safeTrim(v1StudentModel.getLastDocument().getPassportIssueString()))
                .passportNumber(StringUtils.safeTrim(v1StudentModel.getLastDocument().getPassportNumber()))
                .passportSerial(StringUtils.safeTrim(v1StudentModel.getLastDocument().getPassportSerial()))
                .passportType(EPassportType.PASSPORT)
                .build();

        if (!update) {
            passportModel.setCreated(LocalDateTime.now());
            passportModel.setSourceId(v1StudentModel.getSourceId());
        }
        passportModel.setUpdated(LocalDateTime.now());
        passportModel.setStatus(v1StudentModel.getStatus().map());
        s.setPassport(passportModel);

        List<ParentModel> parents = new ArrayList<>();
        if (v1StudentModel.getFatherFullName() != null && v1StudentModel.getFatherFullName().split(" ").length == 3) {
            PhoneModel fatherPhone = null;
            if (v1StudentModel.getLastDocument().getFatherPhone() != null && v1StudentModel.getLastDocument().getFatherPhone().matches(".*\\d+.*")) {
                fatherPhone = PhoneModel.builder()
                        .type(EMobileOperatorType.UNKNOWN)
                        .phone(StringUtils.safeTrim(v1StudentModel.getLastDocument().getFatherPhone())
                        ).build();
            }

            ParentModel father = ParentModel.builder()
                    .student(s)
                    .surname(v1StudentModel.getFatherFullName().split(" ")[0])
                    .name(v1StudentModel.getFatherFullName().split(" ")[1])
                    .patronymic(v1StudentModel.getFatherFullName().split(" ")[2])
                    .job(v1StudentModel.getLastDocument().getFatherJob() != null ? v1StudentModel.getLastDocument().getFatherJob() : null)
                    .phone(fatherPhone)
                    .build();

            parents.add(father);
        }

        if (v1StudentModel.getMotherFullName() != null && v1StudentModel.getMotherFullName().split(" ").length == 3) {
            PhoneModel motherPhone = null;
            if (v1StudentModel.getLastDocument().getMotherPhone() != null && v1StudentModel.getLastDocument().getMotherPhone().matches(".*\\d+.*")) {
                motherPhone = PhoneModel.builder()
                        .type(EMobileOperatorType.UNKNOWN)
                        .phone(StringUtils.safeTrim(v1StudentModel.getLastDocument().getFatherPhone())
                        ).build();
            }

            ParentModel mother = ParentModel.builder()
                    .student(s)
                    .surname(v1StudentModel.getMotherFullName().split(" ")[0])
                    .name(v1StudentModel.getMotherFullName().split(" ")[1])
                    .patronymic(v1StudentModel.getMotherFullName().split(" ")[2])
                    .job(v1StudentModel.getLastDocument().getMotherJob() != null ? v1StudentModel.getLastDocument().getMotherJob() : null)
                    .phone(motherPhone)
                    .build();

            parents.add(mother);
        }

        s.setParents(!parents.isEmpty() ? parents : null);

        s.setEducations(
                v1StudentModel.getLastDocument().getEducations().stream()
                        .map(v1education ->
                                EducationModel.builder()
                                        .student(s)
                                        .education(v1education.getEducation())
                                        .educationDocumentType(v1education.getEducationDocumentType())
                                        .educationDocumentSerial(v1education.getEducationDocumentSerial())
                                        .educationDocumentNumber(v1education.getEducationDocumentNumber())
                                        .build())
                        .toList()
        );

        s.setBirthPlace(v1StudentModel.getLastDocument().getBirthPlace());
        s.setBenefits(v1StudentModel.getLastDocument().getBenefits());

        this.specializationService.getById(v1StudentModel.getSpecialization().getId()).ifPresent(s::setSpecialization);

        if (s.getSpecialization() == null) {
            throw new RuntimeException("Specialization for student with id = %d not found".formatted(v1StudentModel.getId()));
        }

        this.groupService.getById(v1StudentModel.getGroup().getId()).ifPresent(s::setGroup);

        if (s.getGroup() == null) {
            throw new RuntimeException("Group for student with id = %d not found".formatted(v1StudentModel.getId()));
        }

        s.setNeedHostel(v1StudentModel.getLastDocument().isNeedHostel());

        this.hostelRoomService.getById(v1StudentModel.getHostelRoom().getId()).ifPresentOrElse(s::setHostelRoom, () -> s.setHostelRoom(null));

        s.setCheckInDate(v1StudentModel.getCheckInDate());
        s.setEvictionDate(v1StudentModel.getEvictionDate());
        s.setApproved(v1StudentModel.isApproved());
        s.setPhotoUrl(v1StudentModel.getPhotoUrl());

        return s;
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
        this.insertAll(this.convertNotExistsFromDB());
    }
}
