package by.vstu.dean.services;

import by.vstu.dean.future.models.students.StudentModel;
import by.vstu.dean.future.repo.StudentModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    @Autowired
    private StudentModelRepository repository;

    public List<StudentModel> findAll() {
        return this.repository.findAll();
    }

    public List<StudentModel> findAllByGroupId(long id) {
        return this.repository.findAllByGroupId(id);
    }


    public StudentModel findOne(long id) {
        return this.repository.findById(id).orElse(null);
    }
}
