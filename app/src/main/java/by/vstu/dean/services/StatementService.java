package by.vstu.dean.services;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.core.websocket.WSControllerManager;
import by.vstu.dean.models.lessons.StatementModel;
import by.vstu.dean.repo.StatementModelRepository;
import org.javers.core.Javers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatementService extends BaseService<StatementModel, StatementModelRepository> {
    public StatementService(StatementModelRepository repo, Javers javers, WSControllerManager wsControllerManager) {
        super(repo, javers, wsControllerManager);
    }

    public List<Long> getAllDistinctSourceIdForStudent(Long studentSourceId) {
        return this.repo.findDistinctByStatementStudents_Student_SourceId(studentSourceId).stream().map(DBBaseModel::getSourceId).toList();
    }

}
