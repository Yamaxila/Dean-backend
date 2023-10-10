package by.vstu.dean.services.migrate;

import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.models.students.StudentLanguageModel;
import by.vstu.dean.future.repo.StudentLanguageModelRepository;
import by.vstu.dean.old.models.DStudentLanguageModel;
import by.vstu.dean.old.repo.DStudentLanguageModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentLanguageMigrateService extends BaseMigrateService<StudentLanguageModel, DStudentLanguageModel> {

    private final DStudentLanguageModelRepository dStudentLanguageRepository;
    private final StudentLanguageModelRepository studentLanguageRepository;

    @Override
    public Long getLastDBId() {
        return this.studentLanguageRepository.findTopByOrderByIdDesc() == null ? 0 : this.studentLanguageRepository.findTopByOrderByIdDesc().getSourceId();
    }

    @Override
    public List<StudentLanguageModel> convertNotExistsFromDB() {
        return this.convertList(this.dStudentLanguageRepository.findAllByIdAfter(this.getLastDBId()));
    }

    @Override
    public StudentLanguageModel convertSingle(DStudentLanguageModel dStudentLanguageModel) {
        StudentLanguageModel studentLanguageModel = new StudentLanguageModel(dStudentLanguageModel.getName());
        studentLanguageModel.setStatus(EStatus.ACTIVE);
        studentLanguageModel.setSourceId(dStudentLanguageModel.getId());
        return studentLanguageModel;
    }

    @Override
    public List<StudentLanguageModel> convertList(List<DStudentLanguageModel> t) {
        List<StudentLanguageModel> out = new ArrayList<>();
        t.forEach(citizenship -> out.add(this.convertSingle(citizenship)));
        return out;
    }

    @Override
    public StudentLanguageModel insertSingle(StudentLanguageModel t) {
        return this.studentLanguageRepository.saveAndFlush(t);
    }

    @Override
    public List<StudentLanguageModel> insertAll(List<StudentLanguageModel> t) {
        return this.studentLanguageRepository.saveAllAndFlush(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
