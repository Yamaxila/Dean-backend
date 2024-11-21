package by.vstu.old.dean.repo;

import by.vstu.old.dean.OldDBBaseModelRepository;
import by.vstu.old.dean.models.DExamModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DExamModelModelRepository extends OldDBBaseModelRepository<DExamModel> {

    @Override
    @NotNull
    @Cacheable(value = "d_exam", key = "#id")
    Optional<DExamModel> findById(@NotNull Long id);

}