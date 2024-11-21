package by.vstu.old.dean.repo;

import by.vstu.old.dean.OldDBBaseModelRepository;
import by.vstu.old.dean.models.DStudentLanguageModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DStudentLanguageModelRepository extends OldDBBaseModelRepository<DStudentLanguageModel> {

    @Override
    @NotNull
    @Cacheable(value = "d_student_land", key = "#id")
    Optional<DStudentLanguageModel> findById(@NotNull Long id);

}