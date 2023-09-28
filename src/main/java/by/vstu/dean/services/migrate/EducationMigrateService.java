package by.vstu.dean.services.migrate;

import by.vstu.dean.future.models.students.EducationModel;
import by.vstu.dean.future.repo.EducationModelRepository;
import by.vstu.dean.future.repo.StudentModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class EducationMigrateService extends BaseMigrateService<EducationModel, EducationModel> {

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
    public EducationModel convertSingle(EducationModel educationModel) {
        throw new RuntimeException("Not implemented!");
    }

    @Override
    public List<EducationModel> convertList(List<EducationModel> t) {
        throw new RuntimeException("Not implemented!");
    }

    public List<EducationModel> applyStudentIds() {
        List<EducationModel> temp = this.educationModelRepository.findAllByStudentIdIsNull();
        temp.forEach((educationModel) -> {
            educationModel.setStudent(this.studentModelRepository.findBySourceId(educationModel.getSourceId()));
        });
        return temp;
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
