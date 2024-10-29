package by.vstu.old.dean.repo;

import by.vstu.old.dean.OldDBBaseModelRepository;
import by.vstu.old.dean.models.DStudyPlan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DStudyPlanModelRepository extends OldDBBaseModelRepository<DStudyPlan> {
    List<DStudyPlan> findAllByGroupIdAndTeacherIdNotNull(Long id);
}