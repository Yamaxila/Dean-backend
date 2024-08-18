package by.vstu.dean.repo;

import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.models.students.DocumentModel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentModelRepository extends DBBaseModelRepository<DocumentModel> {
    @Query("select s.sourceId from DocumentModel s")
    List<Long> findAllSourceIds();
}