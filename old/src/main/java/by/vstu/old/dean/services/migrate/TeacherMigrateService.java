package by.vstu.old.dean.services.migrate;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.core.utils.StringUtils;
import by.vstu.dean.models.lessons.TeacherModel;
import by.vstu.dean.repo.TeacherDegreeModelRepository;
import by.vstu.dean.services.TeacherService;
import by.vstu.old.dean.models.DTeacherModel;
import by.vstu.old.dean.repo.DTeacherModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherMigrateService extends BaseMigrateService<TeacherModel, DTeacherModel> {

    private final DTeacherModelRepository dTeacherModelRepository;
    private final TeacherService teacherService;
    private final TeacherDegreeModelRepository teacherDegreeModelRepository;

    @Override
    public Long getLastDBId() {
        return this.teacherService.getRepo().findTopByOrderByIdDesc() != null ? this.teacherService.getRepo().findTopByOrderByIdDesc().getSourceId() : 0;
    }

    @Override
    public List<TeacherModel> convertNotExistsFromDB() {
        return this.convertList(this.dTeacherModelRepository.findAllByIdAfter(this.getLastDBId()));
    }

    @Deprecated
    public List<TeacherModel> convertNotExistsFromDB(List<DTeacherModel> dTeacherModels) {
        List<Long> ids = this.teacherService.getAll().stream().map(DBBaseModel::getSourceId).toList();
        List<DTeacherModel> temp = dTeacherModels.stream().filter(p -> !ids.contains(p.getId())).toList();
        return this.convertList(temp);
    }

    @Override
    public TeacherModel convertSingle(DTeacherModel dTeacherModel, boolean update) {

        TeacherModel teacherModel = new TeacherModel();
        teacherModel.setDegree(this.teacherDegreeModelRepository.findByNameLike(dTeacherModel.getDegree() == null ? "..." : dTeacherModel.getDegree().toLowerCase().trim()));
        teacherModel.setSurname(StringUtils.safeTrim(dTeacherModel.getLastName()));
        teacherModel.setName(StringUtils.safeTrim(dTeacherModel.getFirstName()));
        teacherModel.setPatronymic(StringUtils.safeTrim(dTeacherModel.getSecondName()));
        if (!update)
            teacherModel.setCreated(LocalDateTime.now());
        teacherModel.setUpdated(LocalDateTime.now());
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
        return this.teacherService.save(t);
    }

    @Override
    public List<TeacherModel> insertAll(List<TeacherModel> t) {
        return this.teacherService.saveAll(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
