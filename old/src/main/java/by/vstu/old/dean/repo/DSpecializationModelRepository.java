package by.vstu.old.dean.repo;

import by.vstu.old.dean.OldDBBaseModelRepository;
import by.vstu.old.dean.models.DSpecializationModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DSpecializationModelRepository extends OldDBBaseModelRepository<DSpecializationModel> {

    @Override
    @NotNull
    @Cacheable(value = "d_spez", key = "#id")
    Optional<DSpecializationModel> findById(@NotNull Long id);

}