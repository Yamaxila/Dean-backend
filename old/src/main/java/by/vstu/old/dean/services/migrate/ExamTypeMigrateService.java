package by.vstu.old.dean.services.migrate;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.models.lessons.ExamModel;
import by.vstu.dean.repo.ExamModelRepository;
import by.vstu.old.dean.models.DExamModel;
import by.vstu.old.dean.repo.DExamModelModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamTypeMigrateService extends BaseMigrateService<ExamModel, DExamModel> {

    private final DExamModelModelRepository dExamModelModelRepository;
    private final ExamModelRepository examModelRepository;

    @Override
    public Long getLastDBId() {
        return this.examModelRepository.findTopByOrderByIdDesc() == null ? 0 : this.examModelRepository.findTopByOrderByIdDesc().getSourceId();
    }

    @Override
    public List<ExamModel> convertNotExistsFromDB() {
        return this.convertList(this.dExamModelModelRepository.findAllByIdAfter(this.getLastDBId()));
    }

    @Override
    public ExamModel convertSingle(DExamModel dExamModel, boolean update) {
        ExamModel examModel = new ExamModel();
        examModel.setName(dExamModel.getName());
        examModel.setType(dExamModel.getExamType());
        examModel.setStatus(EStatus.ACTIVE);
        examModel.setSourceId(dExamModel.getId());
        if (!update)
            examModel.setCreated(LocalDateTime.now());
        examModel.setUpdated(LocalDateTime.now());

        return examModel;
    }

    @Override
    public List<ExamModel> convertList(List<DExamModel> t) {
        List<ExamModel> out = new ArrayList<>();
        t.forEach(examModel -> out.add(this.convertSingle(examModel)));
        return out;
    }

    @Override
    public ExamModel insertSingle(ExamModel t) {
        return this.examModelRepository.saveAndFlush(t);
    }

    @Override
    public List<ExamModel> insertAll(List<ExamModel> t) {
        return this.examModelRepository.saveAllAndFlush(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
