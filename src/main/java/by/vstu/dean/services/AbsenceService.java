package by.vstu.dean.services;

import by.vstu.dean.future.models.lessons.AbsenceModel;
import by.vstu.dean.future.repo.AbsenceModelRepository;
import org.springframework.stereotype.Service;

@Service
public class AbsenceService extends BaseService<AbsenceModel, AbsenceModelRepository> {
    public AbsenceService(AbsenceModelRepository repo) {
        super(repo);
    }
}
