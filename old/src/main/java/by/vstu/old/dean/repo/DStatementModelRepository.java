package by.vstu.old.dean.repo;

import by.vstu.old.dean.OldDBBaseModelRepository;
import by.vstu.old.dean.models.DStatementModel;
import by.vstu.old.dean.models.repo.DStatementRepoModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DStatementModelRepository extends OldDBBaseModelRepository<DStatementModel> {

    @Override
    @NotNull
    @Cacheable(value = "s_statement", key = "#id")
    Optional<DStatementModel> findById(@NotNull Long id);

    @NotNull
//    @Cacheable(value = "s_statement", key = "#facultyId && #course && #studyPlanId && #globalStatementNumber")
    @Query("select s from DStatementModel s where s.course = :course and s.studyPlanId = :studyPlanId and s.facultyId = :facultyId and s.globalStatementNumber = :globalStatementNumber ")
    List<DStatementModel> findAllByMagic(Integer course, Long studyPlanId, Long facultyId, Integer globalStatementNumber);

    @NotNull
    @Query("select s from DStatementModel s where s.studyPlanId = :studyPlanId and s.facultyId = :facultyId and s.globalStatementNumber = :globalStatementNumber ")
    List<DStatementModel> findAllByLittleMagic(Long studyPlanId, Long facultyId, Integer globalStatementNumber);

    @NotNull
    List<DStatementModel> findAllByStudyPlanId(Long studyPlanId);

    @NotNull
    List<DStatementModel> findAllByStudentId(Long studentId);

    @NotNull
    @Query("select new by.vstu.old.dean.models.repo.DStatementRepoModel(d.facultyId, d.studyPlanId, d.globalStatementNumber) FROM DStatementModel d GROUP BY d.studyPlanId, d.globalStatementNumber, d.facultyId")
    List<DStatementRepoModel> findAllUniqueStatements();

    @NotNull
    @Query("select distinct s from DStatementModel s where s.groupId = :groupId and s.id not in :ids")
    List<DStatementModel> findAllByGroupIdAndIdNotIn(Long groupId, List<Long> ids);

    @NotNull
    @Query("select distinct s from DStatementModel s where s.id in :ids")
    List<DStatementModel> findAllByIdIn(List<Long> ids);

    List<DStatementModel> findByStudyPlanId(Long studyPlanId);

//    Optional<DStatementModel> findFirstByFacultyIdAndGlobalStatementNumberAndStudyPlanIdOrderByIdAsc(Long facultyId, Integer globalStatementNumber, Long studyPlanId);
}
