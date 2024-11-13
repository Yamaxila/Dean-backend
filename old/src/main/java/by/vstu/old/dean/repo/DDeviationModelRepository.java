package by.vstu.old.dean.repo;

import by.vstu.old.dean.OldDBBaseModelRepository;
import by.vstu.old.dean.models.DDeviationModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DDeviationModelRepository extends OldDBBaseModelRepository<DDeviationModel> {

    @Override
    @NotNull
    @Cacheable(value = "d_deviation", key = "#id")
    Optional<DDeviationModel> findById(@NotNull Long id);

}