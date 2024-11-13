package by.vstu.old.dean.repo;

import by.vstu.old.dean.OldDBBaseModelRepository;
import by.vstu.old.dean.models.DAbsenceModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DAbsenceModelRepository extends OldDBBaseModelRepository<DAbsenceModel> {
    @Override
    @NotNull
    @Cacheable(value = "d_absence", key = "#id")
    Optional<DAbsenceModel> findById(@NotNull Long id);
}