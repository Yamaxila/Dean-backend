package by.vstu.dean.services;

import by.vstu.dean.future.models.lessons.TeacherDegreeModel;
import by.vstu.dean.future.repo.TeacherDegreeModelRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherDegreeService extends BaseService<TeacherDegreeModel, TeacherDegreeModelRepository> {
    public TeacherDegreeService(TeacherDegreeModelRepository repo) {
        super(repo);
    }
}
