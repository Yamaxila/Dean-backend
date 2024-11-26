package by.vstu.dean.repo;

import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.models.lessons.StatementModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementModelRepository extends DBBaseModelRepository<StatementModel> {

    @Query("select distinct s.sourceId from StatementStudentMerge s where s.student.sourceId = :sourceId")
    List<Long> findDistinctSourceIdsByStudentSourceId(Long sourceId);

    List<StatementModel> findFirstById(Long id);

    @Query("select (count(*) > 0) from StatementModel s where s.studyPlan.sourceId = :studyPlanSourceId AND s.groupStatementNumber = :globalStatementNumber ")
    Boolean exists(Long globalStatementNumber, Long studyPlanSourceId);

    @Query("select (count(*) > 0) from StatementModel s where s.studyPlan.sourceId = :studyPlanSourceId")
    Boolean exists(Long studyPlanSourceId);

}