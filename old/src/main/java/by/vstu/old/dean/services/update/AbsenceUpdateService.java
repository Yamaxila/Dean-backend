package by.vstu.old.dean.services.update;

import by.vstu.dean.models.lessons.AbsenceModel;
import by.vstu.dean.repo.AbsenceModelRepository;
import by.vstu.dean.services.AbsenceService;
import by.vstu.old.dean.models.DAbsenceModel;
import by.vstu.old.dean.repo.DAbsenceModelRepository;
import by.vstu.old.dean.services.migrate.AbsenceMigrateService;
import org.springframework.stereotype.Service;

@Service
public class AbsenceUpdateService extends BaseUpdateService<DAbsenceModel, DAbsenceModelRepository, AbsenceModel, AbsenceModelRepository, AbsenceService, AbsenceMigrateService> {
    public AbsenceUpdateService(AbsenceModelRepository repo, DAbsenceModelRepository dRepo, AbsenceMigrateService baseMigrateService, AbsenceService service) {
        super(repo, dRepo, baseMigrateService, service);
    }

    @Override
    public void update() {

    }
}
