package by.vstu.migrate.v1.service.migrate;

import by.vstu.dean.models.lessons.TeacherDegreeModel;
import by.vstu.dean.services.TeacherDegreeService;
import by.vstu.migrate.v1.models.lessons.V1TeacherDegreeModel;
import by.vstu.migrate.v1.repo.V1TeacherDegreeModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherDegreeMigrateService extends BaseMigrateService<TeacherDegreeModel, V1TeacherDegreeModel> {

    private TeacherDegreeService teacherDegreeService;
    private V1TeacherDegreeModelRepository v1TeacherDegreeModelRepository;

    @Override
    public List<TeacherDegreeModel> convertNotExistsFromDB() {
        return this.convertList(this.v1TeacherDegreeModelRepository.findAll());
    }

    @Override
    public TeacherDegreeModel convertSingle(V1TeacherDegreeModel v1TeacherDegreeModel, boolean update) {
        TeacherDegreeModel tdm = new TeacherDegreeModel();
        tdm.setId(v1TeacherDegreeModel.getId());
        tdm.setStatus(v1TeacherDegreeModel.getStatus().map());
        tdm.setSourceId(v1TeacherDegreeModel.getSourceId());
        tdm.setCreated(v1TeacherDegreeModel.getCreated());
        tdm.setUpdated(v1TeacherDegreeModel.getUpdated());

        tdm.setName(v1TeacherDegreeModel.getName());
        tdm.setHourPrice(v1TeacherDegreeModel.getHourPrice());

        return tdm;
    }

    @Override
    public TeacherDegreeModel insertSingle(TeacherDegreeModel t) {
        return this.teacherDegreeService.save(t);
    }

    @Override
    public List<TeacherDegreeModel> insertAll(List<TeacherDegreeModel> t) {
        return this.teacherDegreeService.saveAll(t);
    }

    @Override
    public void migrate() {
        System.err.println(this.getClass().getName());
        this.insertAll(this.convertNotExistsFromDB());
    }
}
