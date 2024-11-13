package by.vstu.old.dean.repo;

import by.vstu.old.dean.OldDBBaseModelRepository;
import by.vstu.old.dean.models.DSpecialityModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DSpecialityModelRepository extends OldDBBaseModelRepository<DSpecialityModel> {

    @Override
    @NotNull
    @Cacheable(value = "d_spec", key = "#id")
    Optional<DSpecialityModel> findById(@NotNull Long id);

}