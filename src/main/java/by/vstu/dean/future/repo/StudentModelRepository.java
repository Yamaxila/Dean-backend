package by.vstu.dean.future.repo;

import by.vstu.dean.future.DBBaseModelRepository;
import by.vstu.dean.future.models.students.StudentModel;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentModelRepository extends DBBaseModelRepository<StudentModel> {


    @Query("select s.sourceId from StudentModel s")
    List<Long> findAllSourceIds();

    StudentModel findTopByGroupIdAndSpecializationNotNull(Long id);

    List<StudentModel> findAllByGroupId(Long id);


//    @Query("select s.sourceId from StudentModel s where s.id in ()")
//    List<StudentModel> findAllByIds(List<Long> ids);
}