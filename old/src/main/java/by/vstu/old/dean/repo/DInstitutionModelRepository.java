package by.vstu.old.dean.repo;

import by.vstu.old.dean.OldDBBaseModelRepository;
import by.vstu.old.dean.models.DInstitutionModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DInstitutionModelRepository extends OldDBBaseModelRepository<DInstitutionModel> {

    @Override
    @NotNull
    @Cacheable(value = "d_institution", key = "#id")
    Optional<DInstitutionModel> findById(@NotNull Long id);

}