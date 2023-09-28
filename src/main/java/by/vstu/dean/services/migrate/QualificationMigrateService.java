package by.vstu.dean.services.migrate;

import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.models.specs.QualificationModel;
import by.vstu.dean.future.repo.QualificationModelRepository;
import by.vstu.dean.old.models.DQualificationModel;
import by.vstu.dean.old.repo.DQualificationModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class QualificationMigrateService extends BaseMigrateService<QualificationModel, DQualificationModel> {


    private final DQualificationModelRepository dQualificationRepo;
    private final QualificationModelRepository qualificationRepo;

    @Override
    public Long getLastDBId() {
        return this.qualificationRepo.findTopByOrderByIdDesc() == null ? 0 : this.qualificationRepo.findTopByOrderByIdDesc().getSourceId();
    }

    @Override
    public List<QualificationModel> convertNotExistsFromDB() {

        List<DQualificationModel> temp = new ArrayList<>();
        this.dQualificationRepo.findAllByIdAfter(this.getLastDBId()).forEach(base -> temp.add((DQualificationModel)base));
        return this.convertList(temp);
    }

    @Override
    public QualificationModel convertSingle(DQualificationModel dQualificationModel) {
        QualificationModel qualificationModel = new QualificationModel();
        qualificationModel.setName(dQualificationModel.getNamePart1());
        qualificationModel.setNameGenitive(dQualificationModel.getNamePart2());
        qualificationModel.setStatus(EStatus.ACTIVE);
        qualificationModel.setSourceId(dQualificationModel.getId());
        return qualificationModel;
    }

    @Override
    public List<QualificationModel> convertList(List<DQualificationModel> t) {
        List<QualificationModel> out = new ArrayList<>();

        t.forEach(q -> out.add(this.convertSingle(q)));

        return out;
    }

    @Override
    public QualificationModel insertSingle(QualificationModel t) {
        return this.qualificationRepo.saveAndFlush(t);
    }

    @Override
    public List<QualificationModel> insertAll(List<QualificationModel> t) {
        return this.qualificationRepo.saveAllAndFlush(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
