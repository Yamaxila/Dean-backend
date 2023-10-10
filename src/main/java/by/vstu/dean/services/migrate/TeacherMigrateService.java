package by.vstu.dean.services.migrate;

import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.models.lessons.TeacherModel;
import by.vstu.dean.future.repo.TeacherDegreeModelRepository;
import by.vstu.dean.future.repo.TeacherModelRepository;
import by.vstu.dean.old.models.DTeacherModel;
import by.vstu.dean.old.repo.DTeacherModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherMigrateService extends BaseMigrateService<TeacherModel, DTeacherModel> {

    private final DTeacherModelRepository dTeacherModelRepository;
    private final TeacherModelRepository teacherModelRepository;
    private final TeacherDegreeModelRepository teacherDegreeModelRepository;

    @Override
    public Long getLastDBId() {
        return this.teacherModelRepository.findTopByOrderByIdDesc() != null ? this.teacherModelRepository.findTopByOrderByIdDesc().getSourceId() : 0;
    }

    @Override
    public List<TeacherModel> convertNotExistsFromDB() {
        return this.convertList(this.dTeacherModelRepository.findAllByIdAfter(this.getLastDBId()));
    }

    @Deprecated
    public List<TeacherModel> convertNotExistsFromDB(List<DTeacherModel> dTeacherModels) {
        List<Long> ids = this.teacherModelRepository.findAll().stream().map(DBBaseModel::getSourceId).toList();
        List<DTeacherModel> temp = dTeacherModels.stream().filter(p -> !ids.contains(p.getId())).toList();
        return this.convertList(temp);
    }

    @Override
    public TeacherModel convertSingle(DTeacherModel dTeacherModel) {

        TeacherModel teacherModel = new TeacherModel();
        teacherModel.setDegree(this.teacherDegreeModelRepository.findByNameLike(dTeacherModel.getDegree() == null ? "..." : dTeacherModel.getDegree().toLowerCase().trim()));
        teacherModel.setSurname(dTeacherModel.getLastName());
        teacherModel.setName(dTeacherModel.getFirstName());
        teacherModel.setPatronymic(dTeacherModel.getSecondName());

        teacherModel.setSourceId(dTeacherModel.getId());
        teacherModel.setStatus(EStatus.ACTIVE);
        return teacherModel;
    }

    @Override
    public List<TeacherModel> convertList(List<DTeacherModel> t) {
        List<TeacherModel> temp = new ArrayList<>();

        t.forEach((dTeacherModel) -> temp.add(this.convertSingle(dTeacherModel)));

        return temp;
    }

    @Override
    public TeacherModel insertSingle(TeacherModel t) {
        return this.teacherModelRepository.saveAndFlush(t);
    }

    @Override
    public List<TeacherModel> insertAll(List<TeacherModel> t) {
        return this.teacherModelRepository.saveAllAndFlush(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
