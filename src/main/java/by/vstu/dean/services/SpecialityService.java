package by.vstu.dean.services;

import by.vstu.dean.future.models.specs.SpecialityModel;
import by.vstu.dean.future.repo.SpecialityModelRepository;
import org.springframework.stereotype.Service;

@Service
public class SpecialityService extends BaseService<SpecialityModel, SpecialityModelRepository> {
    public SpecialityService(SpecialityModelRepository repo) {
        super(repo);
    }
}
