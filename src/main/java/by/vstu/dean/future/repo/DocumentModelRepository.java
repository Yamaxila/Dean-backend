package by.vstu.dean.future.repo;

import by.vstu.dean.future.DBBaseModelRepository;
import by.vstu.dean.future.models.students.DocumentModel;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DocumentModelRepository extends DBBaseModelRepository<DocumentModel> {
    @Query("select s.sourceId from DocumentModel s")
    List<Long> findAllSourceIds();
}