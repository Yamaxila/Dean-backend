package by.vstu.dean.services.updates;

import by.vstu.dean.dto.future.students.CitizenshipDTO;
import by.vstu.dean.dto.mapper.CitizenshipMapper;
import by.vstu.dean.future.models.students.CitizenshipModel;
import by.vstu.dean.future.repo.CitizenshipModelRepository;
import by.vstu.dean.old.models.DCitizenshipModel;
import by.vstu.dean.old.repo.DCitizenshipModelRepository;
import by.vstu.dean.services.CitizenshipService;
import by.vstu.dean.services.migrate.CitizenshipMigrateService;
import org.springframework.stereotype.Service;

@Service
public class CitizenshipUpdateService extends BaseUpdateService<CitizenshipDTO, DCitizenshipModel, DCitizenshipModelRepository, CitizenshipModel, CitizenshipMapper, CitizenshipModelRepository, CitizenshipService, CitizenshipMigrateService> {

    public CitizenshipUpdateService(CitizenshipModelRepository repo, DCitizenshipModelRepository dRepo, CitizenshipMigrateService baseMigrateService, CitizenshipService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
