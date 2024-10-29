package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.models.lessons.AbsenceModel;
import by.vstu.dean.repo.AbsenceModelRepository;
import org.javers.core.Javers;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Cacheable("absence")
public class AbsenceService extends BaseService<AbsenceModel, AbsenceModelRepository> {
    public AbsenceService(AbsenceModelRepository repo, Javers javers) {
        super(repo, javers);
    }
}
