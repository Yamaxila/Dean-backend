package by.vstu.dean.old.repo;

import by.vstu.dean.old.OldDBBaseModelRepository;
import by.vstu.dean.old.models.DStudyPlan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DStudyPlanModelRepository extends OldDBBaseModelRepository<DStudyPlan> {
    List<DStudyPlan> findAllByGroupIdAndTeacherIdNotNull(Long id);
}