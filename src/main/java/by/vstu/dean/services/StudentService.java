package by.vstu.dean.services;

import by.vstu.dean.future.models.students.StudentModel;
import by.vstu.dean.future.repo.StudentModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService extends BaseService<StudentModel, StudentModelRepository> {


    public StudentService(StudentModelRepository repo) {
        super(repo);
    }

    public List<StudentModel> findAllByGroupId(long id) {
        return this.repo.findAllByGroupId(id);
    }

}
