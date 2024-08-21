package by.vstu.dean.old.services.migrate;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.utils.StringUtils;
import by.vstu.dean.models.students.StudentLanguageModel;
import by.vstu.dean.old.models.DStudentLanguageModel;
import by.vstu.dean.old.repo.DStudentLanguageModelRepository;
import by.vstu.dean.repo.StudentLanguageModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public StudentLanguageModel convertSingle(DStudentLanguageModel dStudentLanguageModel, boolean update) {
        StudentLanguageModel studentLanguageModel = new StudentLanguageModel(StringUtils.safeTrim(dStudentLanguageModel.getName()));
        studentLanguageModel.setStatus(EStatus.ACTIVE);
        studentLanguageModel.setSourceId(dStudentLanguageModel.getId());
        if (!update)
            studentLanguageModel.setCreated(LocalDateTime.now());
        studentLanguageModel.setUpdated(LocalDateTime.now());
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
