package by.vstu.old.dean.repo;

import by.vstu.old.dean.OldDBBaseModelRepository;
import by.vstu.old.dean.models.DQualificationModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DQualificationModelRepository extends OldDBBaseModelRepository<DQualificationModel> {

    @Override
    @NotNull
    @Cacheable(value = "d_qualification", key = "#id")
    Optional<DQualificationModel> findById(@NotNull Long id);

}