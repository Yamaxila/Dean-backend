package by.vstu.old.dean.repo;

import by.vstu.old.dean.OldDBBaseModelRepository;
import by.vstu.old.dean.models.DGroupModel;
import by.vstu.old.dean.models.DSpecializationModel;
import by.vstu.old.dean.models.DStudentModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DStudentModelRepository extends OldDBBaseModelRepository<DStudentModel> {


    @Query("select s.specialization from DStudentModel s WHERE s.id > ?1")
    List<DSpecializationModel> findAllSpecializations(Long id);

    @Query("select s.group from DStudentModel s WHERE s.id > ?1")
    List<DGroupModel> findAllGroups(Long id);

    List<DStudentModel> findAllByGroupId(Long id);

    List<DStudentModel> findAllByCaseNo(String caseNo);
}