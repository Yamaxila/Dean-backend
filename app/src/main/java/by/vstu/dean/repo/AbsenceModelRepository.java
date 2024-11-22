package by.vstu.dean.repo;

import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.models.lessons.AbsenceModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AbsenceModelRepository extends DBBaseModelRepository<AbsenceModel> {
    List<AbsenceModel> findByStudent_CaseNo(Long caseNo);
}