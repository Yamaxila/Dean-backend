package by.vstu.dean.services;

import by.vstu.dean.future.models.students.CitizenshipModel;
import by.vstu.dean.future.repo.CitizenshipModelRepository;
import org.springframework.stereotype.Service;

@Service
public class CitizenshipService extends BaseService<CitizenshipModel, CitizenshipModelRepository> {
    public CitizenshipService(CitizenshipModelRepository repo) {
        super(repo);
    }
}
