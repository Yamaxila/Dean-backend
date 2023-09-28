package by.vstu.dean.old.repo;

import by.vstu.dean.old.OldDBBaseModelRepository;
import by.vstu.dean.old.models.DStudyPlan;

import java.util.List;

public interface DStudyPlanRepository extends OldDBBaseModelRepository<DStudyPlan> {
    List<DStudyPlan> findAllByGroupIdAndTeacherIdNotNull(Long id);
}