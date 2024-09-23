package by.vstu.dean.services.changes;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.models.changes.ChangeModel;
import by.vstu.dean.repo.ChangeModelRepository;
import org.javers.core.Javers;
import org.springframework.stereotype.Service;

@Service
public class ChangeService extends BaseService<ChangeModel, ChangeModelRepository> {

    public ChangeService(ChangeModelRepository repo, Javers javers) {
        super(repo, javers);
    }
}
