package by.vstu.dean.services.students;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.core.websocket.WSControllerManager;
import by.vstu.dean.models.students.internal.PhoneModel;
import by.vstu.dean.repo.PhoneModelRepository;
import org.javers.core.Javers;
import org.springframework.stereotype.Service;

@Service
public class PhoneService extends BaseService<PhoneModel, PhoneModelRepository> {
    public PhoneService(PhoneModelRepository repo, Javers javers, WSControllerManager tm) {
        super(repo, javers, tm);
    }
}
