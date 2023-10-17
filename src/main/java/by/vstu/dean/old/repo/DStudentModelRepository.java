package by.vstu.dean.old.repo;

import by.vstu.dean.old.OldDBBaseModelRepository;
import by.vstu.dean.old.models.DGroupModel;
import by.vstu.dean.old.models.DSpecializationModel;
import by.vstu.dean.old.models.DStudentModel;
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

}