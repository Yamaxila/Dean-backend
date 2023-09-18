package by.vstu.dean.future.repo;

import by.vstu.dean.future.models.StudentModel;
import by.vstu.dean.future.DBBaseModelRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentModelRepository extends DBBaseModelRepository<StudentModel> {


    @Query("select s.sourceId from StudentModel s")
    List<Long> findAllSourceIds();

//    @Query("select s.sourceId from StudentModel s where s.id in ()")
//    List<StudentModel> findAllByIds(List<Long> ids);
}