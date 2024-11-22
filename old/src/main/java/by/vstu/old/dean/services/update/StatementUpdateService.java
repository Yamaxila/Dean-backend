package by.vstu.old.dean.services.update;

import by.vstu.dean.models.lessons.StatementModel;
import by.vstu.dean.repo.StatementModelRepository;
import by.vstu.dean.services.StatementService;
import by.vstu.old.dean.models.DStatementModel;
import by.vstu.old.dean.repo.DStatementModelRepository;
import by.vstu.old.dean.services.migrate.StatementMigrateService;
import org.springframework.stereotype.Service;

@Service
public class StatementUpdateService extends BaseUpdateService<DStatementModel, DStatementModelRepository, StatementModel, StatementModelRepository, StatementService, StatementMigrateService> {

    private final StatementService statementService;

    public StatementUpdateService(StatementModelRepository repo, DStatementModelRepository dRepo, StatementMigrateService baseMigrateService, StatementService service, MainUpdateService updateService, StatementService statementService) {
        super(repo, dRepo, baseMigrateService, service, updateService);
        this.statementService = statementService;
    }

//    public List<StatementStudentMerge> getUpdates() {
//        this.statementService.getAll().forEach(statement -> {
//
//
//
//        });
//    }


}
