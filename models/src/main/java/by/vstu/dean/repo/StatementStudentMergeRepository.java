package by.vstu.dean.repo;

import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.models.merge.StatementStudentMerge;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatementStudentMergeRepository extends DBBaseModelRepository<StatementStudentMerge> {

    List<StatementStudentMerge> findByStudent_CaseNo(Long caseNo);

    List<StatementStudentMerge> findByStatementId(Long statementId);

    @Query("select s.sourceId from StatementStudentMerge s where s.student.sourceId = :studentSourceId")
    List<Long> findSourceIdsByStudentSourceId(Long studentSourceId);

    @Query("SELECT COUNT(t.id) FROM StatementStudentMerge t")
    Long countAll();

    @NotNull
    Page<StatementStudentMerge> findAll(@NotNull Pageable pageable);

}