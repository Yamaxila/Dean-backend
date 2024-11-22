package by.vstu.dean.repo;

import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.models.merge.StatementStudentMerge;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementStudentMergeRepository extends DBBaseModelRepository<StatementStudentMerge> {

    List<StatementStudentMerge> findByStudent_CaseNo(Long caseNo);

    List<StatementStudentMerge> findByStatementId(Long statementId);

    @Query("select s.sourceId from StatementStudentMerge s where s.student.sourceId = :studentSourceId")
    List<Long> findSourceIdsByStudentSourceId(Long studentSourceId);
}