package by.vstu.old.dean.repo;

import by.vstu.old.dean.OldDBBaseModelRepository;
import by.vstu.old.dean.models.DTeacherModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DTeacherModelRepository extends OldDBBaseModelRepository<DTeacherModel> {


    @Override
    @NotNull
    @Cacheable(value = "d_teacher", key = "#id")
    Optional<DTeacherModel> findById(@NotNull Long id);


}