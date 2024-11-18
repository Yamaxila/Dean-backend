package by.vstu.dean.repo;

import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.models.lessons.StatementModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementModelRepository extends DBBaseModelRepository<StatementModel> {


    List<StatementModel> findDistinctByStatementStudents_Student_SourceId(Long sourceId);
}