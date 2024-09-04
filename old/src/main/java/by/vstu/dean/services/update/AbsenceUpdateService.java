package by.vstu.dean.services.update;

import by.vstu.dean.models.lessons.AbsenceModel;
import by.vstu.dean.models.DAbsenceModel;
import by.vstu.dean.repo.DAbsenceModelRepository;
import by.vstu.dean.services.migrate.AbsenceMigrateService;
import by.vstu.dean.repo.AbsenceModelRepository;
import by.vstu.dean.services.AbsenceService;
import org.springframework.stereotype.Service;

@Service
public class AbsenceUpdateService extends BaseUpdateService<DAbsenceModel, DAbsenceModelRepository, AbsenceModel, AbsenceModelRepository, AbsenceService, AbsenceMigrateService> {
    public AbsenceUpdateService(AbsenceModelRepository repo, DAbsenceModelRepository dRepo, AbsenceMigrateService baseMigrateService, AbsenceService service, MainUpdateService updateService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
    }
}
