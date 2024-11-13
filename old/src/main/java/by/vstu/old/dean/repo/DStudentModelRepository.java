package by.vstu.old.dean.repo;

import by.vstu.old.dean.OldDBBaseModelRepository;
import by.vstu.old.dean.models.DGroupModel;
import by.vstu.old.dean.models.DSpecializationModel;
import by.vstu.old.dean.models.DStudentModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DStudentModelRepository extends OldDBBaseModelRepository<DStudentModel> {


    @Query("select s.specialization from DStudentModel s WHERE s.id > ?1")
    @Cacheable(value = "d_student_spez", key = "#id")
    List<DSpecializationModel> findAllSpecializations(Long id);

    @Query("select s.group from DStudentModel s WHERE s.id > ?1")
    @Cacheable(value = "d_student_group", key = "#id")
    List<DGroupModel> findAllGroups(Long id);

    @Cacheable(value = "d_group_students", key = "#id")
    List<DStudentModel> findAllByGroupId(Long id);

    @Cacheable(value = "d_student", key = "#caseNo")
    List<DStudentModel> findAllByCaseNo(String caseNo);

    @Override
    @NotNull
    @Cacheable(value = "d_student", key = "#id")
    Optional<DStudentModel> findById(@NotNull Long id);
}