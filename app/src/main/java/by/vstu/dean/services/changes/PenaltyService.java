package by.vstu.dean.services.changes;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.models.changes.PenaltyModel;
import by.vstu.dean.repo.PenaltyModelRepository;
import org.javers.core.Javers;
import org.springframework.stereotype.Service;

@Service
public class PenaltyService extends BaseService<PenaltyModel, PenaltyModelRepository> {

    public PenaltyService(PenaltyModelRepository repo, Javers javers) {
        super(repo, javers);
    }

}
