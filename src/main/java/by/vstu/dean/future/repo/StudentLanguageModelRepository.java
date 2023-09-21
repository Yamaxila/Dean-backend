package by.vstu.dean.future.repo;

import by.vstu.dean.future.DBBaseModelRepository;
import by.vstu.dean.future.models.StudentLanguageModel;

public interface StudentLanguageModelRepository extends DBBaseModelRepository<StudentLanguageModel> {
    StudentLanguageModel findBySourceId(Long aLong);
}