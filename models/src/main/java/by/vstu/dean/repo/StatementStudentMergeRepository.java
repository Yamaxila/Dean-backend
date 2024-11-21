package by.vstu.dean.repo;

import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.models.merge.StatementStudentMerge;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementStudentMergeRepository extends DBBaseModelRepository<StatementStudentMerge> {

    List<StatementStudentMerge> findByStudent_CaseNo(Long caseNo);
}