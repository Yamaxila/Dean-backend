package by.vstu.dean.services;

import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.core.websocket.WSControllerManager;
import by.vstu.dean.models.lessons.StatementModel;
import by.vstu.dean.models.merge.StatementStudentMerge;
import by.vstu.dean.repo.StatementModelRepository;
import by.vstu.dean.repo.StatementStudentMergeRepository;
import org.javers.core.Javers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatementService extends BaseService<StatementModel, StatementModelRepository> {

    private final StatementStudentMergeRepository studentMergeRepository;

    public StatementService(StatementModelRepository repo, Javers javers, WSControllerManager wsControllerManager, StatementStudentMergeRepository studentMergeRepository) {
        super(repo, javers, wsControllerManager);
        this.studentMergeRepository = studentMergeRepository;
    }

    public List<Long> getAllDistinctSourceIdForStudent(Long studentSourceId) {
        return this.repo.findDistinctSourceIdsByStudentSourceId(studentSourceId);
    }

    public List<StatementStudentMerge> getAllStudentMergeForStudentByCaseNo(Long caseNo) {
        return studentMergeRepository.findByStudent_CaseNo(caseNo);
    }

    public List<StatementStudentMerge> getAllStudentMergeForStatement(Long statementId) {
        return studentMergeRepository.findByStatementId(statementId);
    }

    public List<StatementStudentMerge> getAllStudentMergeSourceIdsForStatement(Long statementId) {
        return studentMergeRepository.findByStatementId(statementId);
    }

    public boolean hasStatement(Long globalStatementNumber, Long studyPlanSourceId) {
        return this.repo.exists(globalStatementNumber, studyPlanSourceId);
    }

    public boolean hasStatement(Long studyPlanSourceId) {
        return this.repo.exists(studyPlanSourceId);
    }

}
