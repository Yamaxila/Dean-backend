package by.vstu.old.dean.repo;

import by.vstu.old.dean.OldDBBaseModelRepository;
import by.vstu.old.dean.models.DMethodistModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DMethodistModelRepository extends OldDBBaseModelRepository<DMethodistModel> {

    @Override
    @NotNull
    @Cacheable(value = "d_methodist", key = "#id")
    Optional<DMethodistModel> findById(@NotNull Long id);

}