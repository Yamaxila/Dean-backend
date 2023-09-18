package by.vstu.dean.future.repo.paging;

import by.vstu.dean.future.models.StudentModel;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PStudentModelRepository extends PagingAndSortingRepository<StudentModel, Long> {
}
