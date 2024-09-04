package by.vstu.dean.repo;

import by.vstu.old.dean.OldDBBaseModelRepository;
import by.vstu.dean.models.DStudyPlan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DStudyPlanModelRepository extends OldDBBaseModelRepository<DStudyPlan> {
    List<DStudyPlan> findAllByGroupIdAndTeacherIdNotNull(Long id);
}