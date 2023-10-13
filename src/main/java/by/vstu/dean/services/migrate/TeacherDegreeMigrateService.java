package by.vstu.dean.services.migrate;

import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.models.lessons.TeacherDegreeModel;
import by.vstu.dean.future.repo.TeacherDegreeModelRepository;
import by.vstu.dean.old.models.DTeacherModel;
import by.vstu.dean.old.repo.DTeacherModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherDegreeMigrateService extends BaseMigrateService<TeacherDegreeModel, DTeacherModel> {

    private final TeacherDegreeModelRepository teacherDegreeModelRepository;
    private final DTeacherModelRepository teacherModelRepository;

    private final List<TeacherDegreeModel> teacherDegreeModels = new ArrayList<>();

    @Override
    public Long getLastDBId() {
        throw new RuntimeException("Not implemented!");
    }

    @Override
    public List<TeacherDegreeModel> convertNotExistsFromDB() {
        this.teacherDegreeModels.addAll(this.teacherDegreeModelRepository.findAll());

        return this.convertList(this.teacherModelRepository.findAll());
    }

    @Override
    public TeacherDegreeModel convertSingle(DTeacherModel dTeacherModel, boolean update) {
        TeacherDegreeModel teacherDegreeModel;
        boolean notInList = false;
        if (!this.teacherDegreeModels.isEmpty()) {
            teacherDegreeModel = this.teacherDegreeModels.stream().filter(p -> p.getName().equalsIgnoreCase(dTeacherModel.getDegree().trim())).findFirst().orElse(null);
        } else {
            teacherDegreeModel = this.teacherDegreeModelRepository.findByNameLike(dTeacherModel.getDegree().toLowerCase().trim());
            notInList = true;
        }
        if (teacherDegreeModel == null) {

            teacherDegreeModel = new TeacherDegreeModel();

            teacherDegreeModel.setSourceId(0L);
            teacherDegreeModel.setStatus(EStatus.ACTIVE);

            if(!update)
                teacherDegreeModel.setCreated(LocalDateTime.now());
            teacherDegreeModel.setUpdated(LocalDateTime.now());
            teacherDegreeModel.setName(dTeacherModel.getDegree().toLowerCase().trim());
            notInList = true;
        }

        if (notInList)
            this.teacherDegreeModels.add(teacherDegreeModel);

        return teacherDegreeModel;
    }

    @Override
    public List<TeacherDegreeModel> convertList(List<DTeacherModel> t) {
        List<TeacherDegreeModel> out = new ArrayList<>();
        t.stream().filter(p -> p.getDegree() != null).forEach(teacherModel -> out.add(this.convertSingle(teacherModel)));
        return out;
    }

    @Override
    public TeacherDegreeModel insertSingle(TeacherDegreeModel t) {
        return this.teacherDegreeModelRepository.saveAndFlush(t);
    }

    @Override
    public List<TeacherDegreeModel> insertAll(List<TeacherDegreeModel> t) {
        return this.teacherDegreeModelRepository.saveAllAndFlush(t.stream().filter(p -> p.getId() == null).toList());

    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
