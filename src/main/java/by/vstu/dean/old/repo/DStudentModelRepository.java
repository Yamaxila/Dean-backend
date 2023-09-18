package by.vstu.dean.old.repo;

import by.vstu.dean.future.models.SpecializationModel;
import by.vstu.dean.old.OldDBBaseModelRepository;
import by.vstu.dean.old.models.DGroupModel;
import by.vstu.dean.old.models.DSpecializationModel;
import by.vstu.dean.old.models.DStudentModel;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DStudentModelRepository extends OldDBBaseModelRepository<DStudentModel> {


    @Query("select s.specialization from DStudentModel s WHERE s.id > ?1")
    List<DSpecializationModel> findAllSpecializations(Long id);

    @Query("select s.group from DStudentModel s WHERE s.id > ?1")
    List<DGroupModel> findAllGroups(Long id);

    List<DStudentModel> findAllByGroupId(Long id);

    @Query("select s.id from DStudentModel s WHERE s.group.name in (?1)")
    List<DStudentModel> findAllByGroupList(String list);
}