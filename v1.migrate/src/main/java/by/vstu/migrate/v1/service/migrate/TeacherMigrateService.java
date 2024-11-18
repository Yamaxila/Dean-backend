package by.vstu.migrate.v1.service.migrate;

import by.vstu.dean.models.lessons.TeacherModel;
import by.vstu.dean.services.TeacherDegreeService;
import by.vstu.dean.services.TeacherService;
import by.vstu.migrate.v1.models.lessons.V1TeacherModel;
import by.vstu.migrate.v1.repo.V1TeacherModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherMigrateService extends BaseMigrateService<TeacherModel, V1TeacherModel> {

    private final TeacherService teacherService;
    private final V1TeacherModelRepository v1TeacherModelRepository;

    private final TeacherDegreeService tdmService;

    @Override
    public List<TeacherModel> convertNotExistsFromDB() {
        return this.convertList(this.v1TeacherModelRepository.findAll());
    }

    @Override
    public TeacherModel convertSingle(V1TeacherModel v1TeacherModel, boolean update) {
        TeacherModel t = new TeacherModel();
        t.setId(v1TeacherModel.getId());
        t.setSourceId(v1TeacherModel.getSourceId());
        t.setStatus(v1TeacherModel.getStatus().map());
        t.setCreated(v1TeacherModel.getCreated());
        t.setUpdated(v1TeacherModel.getUpdated());

        t.setSurname(v1TeacherModel.getSurname());
        t.setName(v1TeacherModel.getName());
        t.setPatronymic(v1TeacherModel.getPatronymic());
        this.tdmService.getById(v1TeacherModel.getDegree().getId()).ifPresent(t::setDegree);

        if (t.getDegree() == null)
            throw new RuntimeException("Degree for teacher with id = %d is null".formatted(v1TeacherModel.getId()));

        t.setPhotoUrl(null);

        return t;
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
