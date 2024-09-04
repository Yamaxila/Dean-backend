package by.vstu.dean.services.migrate;

import by.vstu.dean.models.students.EducationModel;
import by.vstu.dean.models.DStudentModel;
import by.vstu.dean.repo.EducationModelRepository;
import by.vstu.dean.repo.StudentModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationMigrateService extends BaseMigrateService<EducationModel, DStudentModel> {

    private final EducationModelRepository educationModelRepository;
    private final StudentModelRepository studentModelRepository;

    @Override
    public Long getLastDBId() {
        throw new RuntimeException("Not implemented!");
    }

    @Override
    public List<EducationModel> convertNotExistsFromDB() {
        throw new RuntimeException("Not implemented!");
    }

    @Override
    public EducationModel convertSingle(DStudentModel educationModel, boolean update) {
        throw new RuntimeException("Not implemented!");
    }

    @Override
    public List<EducationModel> convertList(List<DStudentModel> t) {
        throw new RuntimeException("Not implemented!");
    }

    public List<EducationModel> applyStudentIds() {
        List<EducationModel> temp = this.educationModelRepository.findAllByStudentIdIsNull();
        List<EducationModel> out = new ArrayList<>();
        temp.forEach((educationModel) -> {
            educationModel.setStudent(this.studentModelRepository.findBySourceId(educationModel.getSourceId()));
            if(educationModel.getUpdated() == null)
                educationModel.setUpdated(LocalDateTime.now());
            out.add(educationModel);
        });
        return out;
    }

    @Override
    public EducationModel insertSingle(EducationModel t) {
        return this.educationModelRepository.saveAndFlush(t);
    }

    @Override
    public List<EducationModel> insertAll(List<EducationModel> t) {
        return this.educationModelRepository.saveAllAndFlush(t);
    }

    @Override
    public void migrate() {

    }
}
