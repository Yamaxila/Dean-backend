package by.vstu.old.dean.repo;

import by.vstu.old.dean.OldDBBaseModelRepository;
import by.vstu.old.dean.models.DDisciplineModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DDisciplineModelRepository extends OldDBBaseModelRepository<DDisciplineModel> {

    @Override
    @NotNull
    @Cacheable(value = "d_discipline", key = "#id")
    Optional<DDisciplineModel> findById(@NotNull Long id);

}