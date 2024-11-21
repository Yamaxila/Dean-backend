package by.vstu.old.dean.repo;

import by.vstu.old.dean.OldDBBaseModelRepository;
import by.vstu.old.dean.models.DStudyPlan;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DStudyPlanModelRepository extends OldDBBaseModelRepository<DStudyPlan> {
    List<DStudyPlan> findAllByGroupIdAndTeacherIdNotNull(Long id);


    @Override
    @NotNull
    @Cacheable(value = "d_study_plan", key = "#id")
    Optional<DStudyPlan> findById(@NotNull Long id);

    @NotNull
    List<DStudyPlan> findAllByGroupId(Long groupId);

    @NotNull
    @Query("select distinct s from DStudyPlan s where s.groupId = :groupId and s.id not in :studyPlans")
    List<DStudyPlan> findAllByGroupIdAndIdNotIn(Long groupId, List<Long> studyPlans);

}