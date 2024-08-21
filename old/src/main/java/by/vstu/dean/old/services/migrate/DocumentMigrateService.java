package by.vstu.dean.old.services.migrate;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.utils.StringUtils;
import by.vstu.dean.models.students.*;
import by.vstu.dean.old.models.DStudentModel;
import by.vstu.dean.repo.CitizenshipModelRepository;
import by.vstu.dean.repo.InstitutionModelRepository;
import by.vstu.dean.repo.StudentLanguageModelRepository;
import by.vstu.dean.services.DocumentService;
import by.vstu.dean.services.EducationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DocumentMigrateService extends BaseMigrateService<DocumentModel, DStudentModel> {

    private final InstitutionModelRepository institutionModelRepository;
    private final CitizenshipModelRepository citizenshipModelRepository;
    private final StudentLanguageModelRepository studentLanguageModelRepository;
    private final EducationMigrateService educationMigrateService;
    private final EducationService educationService;

    private final ArrayList<CitizenshipModel> citizenshipModels = new ArrayList<>();
    private final ArrayList<StudentLanguageModel> studentLanguageModels = new ArrayList<>();
    private final ArrayList<InstitutionModel> institutionModels = new ArrayList<>();
    private final ArrayList<DocumentModel> documentModels = new ArrayList<>();

    private final DocumentService documentService;

    @Override
    public Long getLastDBId() {
        return this.documentService.getRepo().findTopByOrderByIdDesc() == null ? 19000 : this.documentService.getRepo().findTopByOrderByIdDesc().getSourceId();
    }

    @Override
    public List<DocumentModel> convertNotExistsFromDB() {
        throw new RuntimeException("Not implemented!");
    }

    @Override
    public DocumentModel convertSingle(DStudentModel dStudentModel, boolean update) {

        if (this.citizenshipModels.isEmpty())
            this.citizenshipModels.addAll(this.citizenshipModelRepository.findAll());

        if (this.studentLanguageModels.isEmpty())
            this.studentLanguageModels.addAll(this.studentLanguageModelRepository.findAll());

        if (this.institutionModels.isEmpty())
            this.institutionModels.addAll(this.institutionModelRepository.findAll());


        DocumentModel documentModel = new DocumentModel();

        documentModel.setLastSurname(StringUtils.safeTrim(dStudentModel.getLastSurname()));
        documentModel.setFullNameL(StringUtils.safeTrim(dStudentModel.getFullNameL()));
        documentModel.setFirstNameL(StringUtils.safeTrim(dStudentModel.getFullNameL()));
        documentModel.setCitizenshipString(StringUtils.safeTrim(dStudentModel.getCitizenshipString()));

        Optional<CitizenshipModel> citizenship = this.citizenshipModels.stream().filter(p -> p.getSourceId().equals(dStudentModel.getCitizenship() == null ? 0 : Long.valueOf(dStudentModel.getCitizenship()))).findFirst();

        if (citizenship.isEmpty())
            throw new RuntimeException("Citizenship not found for student with sourceId = " + dStudentModel.getId());

        Optional<StudentLanguageModel> language = this.studentLanguageModels.stream().filter(p -> p.getSourceId().equals(dStudentModel.getStudentLanguage() == null ? 0 : Long.valueOf(dStudentModel.getStudentLanguage()))).findFirst();

        if (language.isEmpty())
            throw new RuntimeException("Language not found for student with sourceId = " + dStudentModel.getId());

        Optional<InstitutionModel> institution = this.institutionModels.stream().filter(p -> p.getSourceId().equals(dStudentModel.getInstitution() == null ? 0 : dStudentModel.getInstitution().getId())).findFirst();

        if (institution.isEmpty())
            throw new RuntimeException("Institution not found for student with sourceId = " + dStudentModel.getId());

        documentModel.setCitizenship(citizenship.get());
        documentModel.setStudentLanguage(language.get());
        documentModel.setInstitution(institution.get());
        documentModel.setStudentLanguageString(StringUtils.safeTrim(dStudentModel.getStudentLanguageString()));
        documentModel.setCaseNo(Long.parseLong(dStudentModel.getCaseNo().replaceAll("[^0-9]", "")));
//        documentModel.setDocumentNumber(dStudentModel.getDocumentNumber() == null ? -1 : Long.parseLong(dStudentModel.getDocumentNumber()));
        documentModel.setBenefits(dStudentModel.getBenefits());
        documentModel.setBirthPlace(dStudentModel.getBirthPlace());
        documentModel.setBirthDate(dStudentModel.getBirthDate() == null ? null : dStudentModel.getBirthDate().toLocalDate());
        documentModel.setEnrollDate(dStudentModel.getEnrollmentDate() == null ? null : dStudentModel.getEnrollmentDate().toLocalDate());

        List<EducationModel> educations = this.educationService.getAllBySourceId(dStudentModel.getId());

        if(educations == null || educations.isEmpty()) {
            educations = new ArrayList<>();

            EducationModel education1 = new EducationModel();
            EducationModel education2 = new EducationModel();
            EducationModel education3 = new EducationModel();

            education1.setEducation(dStudentModel.getEducation1() == null || dStudentModel.getEducation1().isEmpty() ? "" : StringUtils.safeTrim(dStudentModel.getEducation1()));
            education2.setEducation(dStudentModel.getEducation2() == null || dStudentModel.getEducation2().isEmpty() ? "" : StringUtils.safeTrim(dStudentModel.getEducation2()));
            education3.setEducation(dStudentModel.getEducation3() == null || dStudentModel.getEducation3().isEmpty() ? "" : StringUtils.safeTrim(dStudentModel.getEducation3()));

            education1.setEducationDocumentSerial(dStudentModel.getEducation1DocumentSerial() == null || dStudentModel.getEducation1DocumentSerial().isEmpty() ? "" : StringUtils.safeTrim(dStudentModel.getEducation1DocumentSerial()));
            education2.setEducationDocumentSerial(dStudentModel.getEducation2DocumentSerial() == null || dStudentModel.getEducation2DocumentSerial().isEmpty() ? "" : StringUtils.safeTrim(dStudentModel.getEducation2DocumentSerial()));
            education3.setEducationDocumentSerial(dStudentModel.getEducation3DocumentSerial() == null || dStudentModel.getEducation3DocumentSerial().isEmpty() ? "" : StringUtils.safeTrim(dStudentModel.getEducation3DocumentSerial()));

            education1.setEducationDocumentNumber(dStudentModel.getEducation1DocumentNumber() == null || dStudentModel.getEducation1DocumentNumber().isEmpty() ? "" : StringUtils.safeTrim(dStudentModel.getEducation1DocumentNumber()));
            education2.setEducationDocumentNumber(dStudentModel.getEducation2DocumentNumber() == null || dStudentModel.getEducation2DocumentNumber().isEmpty() ? "" : StringUtils.safeTrim(dStudentModel.getEducation2DocumentNumber()));
            education3.setEducationDocumentNumber(dStudentModel.getEducation3DocumentNumber() == null || dStudentModel.getEducation3DocumentNumber().isEmpty() ? "" : StringUtils.safeTrim(dStudentModel.getEducation3DocumentNumber()));

            education1.setEducationDocumentType(dStudentModel.getEducation1DocumentType() == null || dStudentModel.getEducation1DocumentType().isEmpty() ? "" : StringUtils.safeTrim(dStudentModel.getEducation1DocumentType()));
            education2.setEducationDocumentType(dStudentModel.getEducation2DocumentType() == null || dStudentModel.getEducation2DocumentType().isEmpty() ? "" : StringUtils.safeTrim(dStudentModel.getEducation2DocumentType()));
            education3.setEducationDocumentType(dStudentModel.getEducation3DocumentType() == null || dStudentModel.getEducation3DocumentType().isEmpty() ? "" : StringUtils.safeTrim(dStudentModel.getEducation3DocumentType()));

            education1.setStatus(dStudentModel.isExpelled() ? EStatus.DELETED : EStatus.ACTIVE);
            education2.setStatus(dStudentModel.isExpelled() ? EStatus.DELETED : EStatus.ACTIVE);
            education3.setStatus(dStudentModel.isExpelled() ? EStatus.DELETED : EStatus.ACTIVE);

            education1.setSourceId(dStudentModel.getId());
            education2.setSourceId(dStudentModel.getId());
            education3.setSourceId(dStudentModel.getId());

            if (education1.getEducation() != null || education1.getEducationDocumentSerial() != null || education1.getEducationDocumentNumber() != null || education1.getEducationDocumentType() != null)
                educations.add(education1);
            if (education2.getEducation() != null || education2.getEducationDocumentSerial() != null || education2.getEducationDocumentNumber() != null || education2.getEducationDocumentType() != null)
                educations.add(education2);
            if (education3.getEducation() != null || education3.getEducationDocumentSerial() != null || education3.getEducationDocumentNumber() != null || education3.getEducationDocumentType() != null)
                educations.add(education3);

            this.educationMigrateService.insertAll(educations);
        }

        documentModel.setNeedHostel(!(dStudentModel.getHostel() == null ||
                dStudentModel.getHostel().isEmpty() ||
                dStudentModel.getHostel().isBlank() ||
                dStudentModel.getHostel().equals("0")));

        documentModel.setEducationString(dStudentModel.getEducationString() == null || dStudentModel.getEducationString().isEmpty() ? "" : StringUtils.safeTrim(dStudentModel.getEducationString()));
        documentModel.setEducationYearEnd(dStudentModel.getEducationYearEnd());

        documentModel.setJob(StringUtils.safeTrim(dStudentModel.getJob()));
        documentModel.setFatherJob(StringUtils.safeTrim(dStudentModel.getFatherJob()));
        documentModel.setMotherJob(StringUtils.safeTrim(dStudentModel.getMotherJob()));
        documentModel.setJobExperience(dStudentModel.getJobExperience());

        documentModel.setFatherPhone(StringUtils.safeTrim(dStudentModel.getFatherPhone()));
        documentModel.setMotherPhone(StringUtils.safeTrim(dStudentModel.getMotherPhone()));

        documentModel.setRegCity(StringUtils.safeTrim(dStudentModel.getRegCity()));
        documentModel.setRegCity2(StringUtils.safeTrim(dStudentModel.getRegCity2()));
        documentModel.setRegFlat(StringUtils.safeTrim(dStudentModel.getRegFlat()));
        documentModel.setRegHouse(StringUtils.safeTrim(dStudentModel.getRegHouse()));
        documentModel.setRegHousePart(StringUtils.safeTrim(dStudentModel.getRegHousePart()));
        documentModel.setRegIndex(StringUtils.safeTrim(dStudentModel.getRegIndex()));
        documentModel.setRegStreet(StringUtils.safeTrim(dStudentModel.getRegStreet()));
        documentModel.setRegRegion(StringUtils.safeTrim(dStudentModel.getRegRegion()));
        documentModel.setRegState(StringUtils.safeTrim(dStudentModel.getRegState()));

        documentModel.setPassportId(StringUtils.safeTrim(dStudentModel.getPassportId()));
        documentModel.setPassportNumber(StringUtils.safeTrim(dStudentModel.getPassportNumber()));
        documentModel.setPassportSerial(StringUtils.safeTrim(dStudentModel.getPassportSerial()));
        documentModel.setPassportIssueDate(dStudentModel.getPassportIssueDate() != null ? dStudentModel.getPassportIssueDate().toLocalDate() : null);
        documentModel.setPassportIssueString(StringUtils.safeTrim(dStudentModel.getPassportIssueString()));

        documentModel.setEmail(StringUtils.safeTrim(dStudentModel.getEmail()));
        documentModel.setMove(dStudentModel.getMove() == 1);
        documentModel.setStateSupport(dStudentModel.getStateSupport() == 1);

        //documentModel.setRegCityType(dStudentModel.getRegCity2());


        documentModel.setSourceId(dStudentModel.getId());
        documentModel.setStatus(dStudentModel.isExpelled() ? EStatus.DELETED : EStatus.ACTIVE);
        documentModel.setMigrateDate(LocalDate.now());

        if (!update)
            documentModel.setCreated(LocalDateTime.now());
        documentModel.setUpdated(LocalDateTime.now());

        return documentModel;
    }

    @Override
    public List<DocumentModel> convertList(List<DStudentModel> t) {
        List<DocumentModel> out = new ArrayList<>();
        t.forEach(student -> out.add(this.convertSingle(student)));

        this.citizenshipModels.clear();
        this.studentLanguageModels.clear();
        this.institutionModels.clear();

        return out;
    }

    @Override
    public DocumentModel insertSingle(DocumentModel t) {
        return this.documentService.save(t);
    }

    @Override
    public List<DocumentModel> insertAll(List<DocumentModel> t) {
        return this.documentService.saveAll(t);
    }

    public Optional<DocumentModel> tryFindDocument(DStudentModel dStudent) {
        if(this.documentModels.isEmpty())
            this.documentModels.addAll(this.documentService.getAll());


        return this.documentModels.stream().filter(p ->
                        Objects.equals(p.getSourceId(), dStudent.getId())
                        || p.getPassportId().equalsIgnoreCase(dStudent.getPassportId())
                ).findFirst();
    }

    public void cleanup() {
        this.institutionModels.clear();
        this.citizenshipModels.clear();
        this.studentLanguageModels.clear();
        this.documentModels.clear();
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        throw new RuntimeException("Not implemented!");

    }
}
