package by.vstu.dean.services;

import by.vstu.dean.future.models.lessons.TeacherModel;
import by.vstu.dean.future.repo.TeacherModelRepository;
import org.springframework.stereotype.Service;

@Service
public class TeacherService extends BaseService<TeacherModel, TeacherModelRepository> {
    public TeacherService(TeacherModelRepository repo) {
        super(repo);
    }

}
