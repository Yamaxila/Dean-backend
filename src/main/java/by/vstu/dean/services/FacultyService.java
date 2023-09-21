package by.vstu.dean.services;

import by.vstu.dean.future.models.FacultyModel;
import by.vstu.dean.future.repo.FacultyModelRepository;
import by.vstu.dean.services.common.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyService {

    @Autowired
    private FacultyModelRepository repository;

    public List<FacultyModel> findAll() {
        return this.repository.findAll();
    }


    public FacultyModel findOne(long id) {
        return this.repository.findById(id).orElse(null);
    }
}
