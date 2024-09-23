package by.vstu.dean.services.students;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.models.students.internal.AddressModel;
import by.vstu.dean.repo.AddressModelRepository;
import org.javers.core.Javers;
import org.springframework.stereotype.Service;

@Service
public class AddressService extends BaseService<AddressModel, AddressModelRepository> {
    public AddressService(AddressModelRepository repo, Javers javers) {
        super(repo, javers);
    }
}
