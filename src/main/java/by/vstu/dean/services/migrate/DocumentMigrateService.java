package by.vstu.dean.services.migrate;

import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.models.DocumentModel;
import by.vstu.dean.future.repo.CitizenshipModelRepository;
import by.vstu.dean.future.repo.DocumentModelRepository;
import by.vstu.dean.future.repo.InstitutionModelRepository;
import by.vstu.dean.future.repo.StudentLanguageModelRepository;
import by.vstu.dean.old.models.DStudentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentMigrateService extends BaseMigrateService<DocumentModel, DStudentModel> {

    private final InstitutionModelRepository institutionModelRepository;
    private final CitizenshipModelRepository citizenshipModelRepository;
    private final StudentLanguageModelRepository studentLanguageModelRepository;
    private final DocumentModelRepository documentModelRepository;

    @Override
    public Long getLastDBId() {
        return this.documentModelRepository.findTopByOrderByIdDesc() == null ? 19000 : this.documentModelRepository.findTopByOrderByIdDesc().getSourceId();
    }

    @Override
    public List<DocumentModel> convertNotExistsFromDB() {
        throw new RuntimeException("Not implemented!");
    }

    @Override
    public DocumentModel convertSingle(DStudentModel dStudentModel) {


        DocumentModel documentModel = new DocumentModel();

        documentModel.setLastSurname(dStudentModel.getLastSurname());
        documentModel.setFullNameL(dStudentModel.getFullNameL());
        documentModel.setFirstNameL(dStudentModel.getFullNameL());
        documentModel.setCitizenshipString(dStudentModel.getCitizenshipString());
        documentModel.setCitizenship(this.citizenshipModelRepository.findBySourceId(dStudentModel.getCitizenship() == null ? 0 : Long.valueOf(dStudentModel.getCitizenship())));
        documentModel.setStudentLanguage(this.studentLanguageModelRepository.findBySourceId(dStudentModel.getStudentLanguage() == null ? 0 : Long.valueOf(dStudentModel.getStudentLanguage())));
        documentModel.setInstitution(this.institutionModelRepository.findBySourceId(dStudentModel.getInstitution() != null ? dStudentModel.getInstitution().getId() : null));
        documentModel.setStudentLanguageString(dStudentModel.getStudentLanguageString());
        documentModel.setCaseNo(Long.parseLong(dStudentModel.getCaseNo().replaceAll("[^0-9]","")));
//        documentModel.setDocumentNumber(dStudentModel.getDocumentNumber() == null ? -1 : Long.parseLong(dStudentModel.getDocumentNumber()));
        documentModel.setBenefits(dStudentModel.getBenefits());
        documentModel.setBirthPlace(dStudentModel.getBirthPlace());
        documentModel.setBirthDate(dStudentModel.getBirthDate() == null ? null : dStudentModel.getBirthDate().toLocalDate());
        documentModel.setEnrollDate(dStudentModel.getEnrollmentDate() == null ? null : dStudentModel.getEnrollmentDate().toLocalDate());
        documentModel.setEducation1(dStudentModel.getEducation1() == null  || dStudentModel.getEducation1().isEmpty() ? null : dStudentModel.getEducation1());
        documentModel.setEducation2(dStudentModel.getEducation2() == null  || dStudentModel.getEducation2().isEmpty() ? null : dStudentModel.getEducation2());
        documentModel.setEducation3(dStudentModel.getEducation3() == null  || dStudentModel.getEducation3().isEmpty() ? null : dStudentModel.getEducation3());
        documentModel.setEducation1DocumentSerial(dStudentModel.getEducation1DocumentSerial() == null  || dStudentModel.getEducation1DocumentSerial().isEmpty() ? null : dStudentModel.getEducation1DocumentSerial());
        documentModel.setEducation1DocumentSerial(dStudentModel.getEducation2DocumentSerial() == null  || dStudentModel.getEducation2DocumentSerial().isEmpty() ? null : dStudentModel.getEducation2DocumentSerial());
        documentModel.setEducation1DocumentSerial(dStudentModel.getEducation3DocumentSerial() == null  || dStudentModel.getEducation3DocumentSerial().isEmpty() ? null : dStudentModel.getEducation3DocumentSerial());
        documentModel.setEducation1DocumentNumber(dStudentModel.getEducation1DocumentNumber() == null  || dStudentModel.getEducation1DocumentNumber().isEmpty() ? null : dStudentModel.getEducation1DocumentNumber());
        documentModel.setEducation1DocumentNumber(dStudentModel.getEducation2DocumentNumber() == null  || dStudentModel.getEducation2DocumentNumber().isEmpty() ? null : dStudentModel.getEducation2DocumentNumber());
        documentModel.setEducation1DocumentNumber(dStudentModel.getEducation3DocumentNumber() == null  || dStudentModel.getEducation3DocumentNumber().isEmpty() ? null : dStudentModel.getEducation3DocumentNumber());
        documentModel.setEducation1DocumentType(dStudentModel.getEducation1DocumentType() == null  || dStudentModel.getEducation1DocumentType().isEmpty() ? null : dStudentModel.getEducation1DocumentType());
        documentModel.setEducation2DocumentType(dStudentModel.getEducation2DocumentType() == null  || dStudentModel.getEducation2DocumentType().isEmpty() ? null : dStudentModel.getEducation2DocumentType());
        documentModel.setEducation3DocumentType(dStudentModel.getEducation3DocumentType() == null  || dStudentModel.getEducation3DocumentType().isEmpty() ? null : dStudentModel.getEducation3DocumentType());
        documentModel.setEducationString(dStudentModel.getEducationString() == null  || dStudentModel.getEducationString().isEmpty() ? null : dStudentModel.getEducationString());
        documentModel.setEducationYearEnd(dStudentModel.getEducationYearEnd());

        documentModel.setJob(dStudentModel.getJob());
        documentModel.setFatherJob(dStudentModel.getFatherJob());
        documentModel.setMotherJob(dStudentModel.getMotherJob());
        documentModel.setJobExperience(dStudentModel.getJobExperience());

        documentModel.setFatherPhone(dStudentModel.getFatherPhone());
        documentModel.setMotherPhone(dStudentModel.getMotherPhone());

        documentModel.setRegCity(dStudentModel.getRegCity());
        documentModel.setRegCity2(dStudentModel.getRegCity2());
        documentModel.setRegFlat(dStudentModel.getRegFlat());
        documentModel.setRegHouse(dStudentModel.getRegHouse());
        documentModel.setRegHousePart(dStudentModel.getRegHousePart());
        documentModel.setRegIndex(dStudentModel.getRegIndex());
        documentModel.setRegStreet(dStudentModel.getRegStreet());
        documentModel.setRegRegion(dStudentModel.getRegRegion());
        documentModel.setRegState(dStudentModel.getRegState());

        documentModel.setPassportId(dStudentModel.getPassportId());
        documentModel.setPassportNumber(dStudentModel.getPassportNumber());
        documentModel.setPassportSerial(dStudentModel.getPassportSerial());
        documentModel.setPassportIssueDate(dStudentModel.getPassportIssueDate() != null ? dStudentModel.getPassportIssueDate().toLocalDate() : null);
        documentModel.setPassportIssueString(dStudentModel.getPassportIssueString());

        documentModel.setEmail(dStudentModel.getEmail());
        documentModel.setMove(dStudentModel.getMove() == 1);
        documentModel.setStateSupport(dStudentModel.getStateSupport() == 1);

        //documentModel.setRegCityType(dStudentModel.getRegCity2());


        documentModel.setSourceId(dStudentModel.getId());
        documentModel.setStatus(dStudentModel.isExpelled() ? EStatus.DELETED : EStatus.ACTIVE);
        documentModel.setMigrateDate(LocalDate.now());

        return documentModel;
    }

    @Override
    public List<DocumentModel> convertList(List<DStudentModel> t) {
        List<DocumentModel> out = new ArrayList<>();

        t.forEach(student -> out.add(this.convertSingle(student)));

        return out;
    }

    @Override
    public DocumentModel insertSingle(DocumentModel t) {
        return this.documentModelRepository.saveAndFlush(t);
    }

    @Override
    public List<DocumentModel> insertAll(List<DocumentModel> t) {
        return this.documentModelRepository.saveAllAndFlush(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        throw new RuntimeException("Not implemented!");

    }
}
