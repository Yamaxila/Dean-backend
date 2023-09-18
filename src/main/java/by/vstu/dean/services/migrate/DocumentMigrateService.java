package by.vstu.dean.services.migrate;

import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.models.DocumentModel;
import by.vstu.dean.future.models.GroupModel;
import by.vstu.dean.future.models.StudentModel;
import by.vstu.dean.future.repo.DocumentModelRepository;
import by.vstu.dean.future.repo.StudentModelRepository;
import by.vstu.dean.future.repo.paging.PStudentModelRepository;
import by.vstu.dean.old.models.DStudentModel;
import by.vstu.dean.old.repo.DStudentModelRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentMigrateService extends BaseMigrateService<DocumentModel, DStudentModel> {

    private final DStudentModelRepository dStudentModelRepository;
    private final StudentModelRepository studentModelRepository;
    private final PStudentModelRepository pStudentModelRepository;
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
        documentModel.setFirstNameL(dStudentModel.getFirstNameL());
        documentModel.setCaseNo(Long.parseLong(dStudentModel.getCaseNo().replaceAll("[^0-9]","")));
//        documentModel.setDocumentNumber(dStudentModel.getDocumentNumber() == null ? -1 : Long.parseLong(dStudentModel.getDocumentNumber()));
        documentModel.setBenefits(dStudentModel.getBenefits());
        documentModel.setBirthPlace(dStudentModel.getBirthPlace());
        documentModel.setBirthDate(dStudentModel.getBirthDate() == null ? null : dStudentModel.getBirthDate().toLocalDate());
        documentModel.setEnrollDate(dStudentModel.getEnrollmentDate() == null ? null : dStudentModel.getEnrollmentDate().toLocalDate());
        documentModel.setEducation1(dStudentModel.getEducation1());
        documentModel.setEducation2(dStudentModel.getEducation2());
        documentModel.setEducation3(dStudentModel.getEducation3());
        documentModel.setEducation1DocumentSerial(dStudentModel.getEducation1DocumentSerial());
        documentModel.setEducation1DocumentSerial(dStudentModel.getEducation2DocumentSerial());
        documentModel.setEducation1DocumentSerial(dStudentModel.getEducation3DocumentSerial());
        documentModel.setEducation1DocumentNumber(dStudentModel.getEducation1DocumentNumber());
        documentModel.setEducation1DocumentNumber(dStudentModel.getEducation2DocumentNumber());
        documentModel.setEducation1DocumentNumber(dStudentModel.getEducation3DocumentNumber());
        documentModel.setEducation1DocumentType(dStudentModel.getEducation1DocumentType());
        documentModel.setEducation2DocumentType(dStudentModel.getEducation2DocumentType());
        documentModel.setEducation3DocumentType(dStudentModel.getEducation3DocumentType());
        documentModel.setEducationString(dStudentModel.getEducationString());
        documentModel.setEducationYearEnd(dStudentModel.getEducationYearEnd());

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
