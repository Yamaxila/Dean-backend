package by.vstu.old.dean.repo;

import by.vstu.old.dean.OldDBBaseModelRepository;
import by.vstu.old.dean.models.DGroupModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DGroupModelRepository extends OldDBBaseModelRepository<DGroupModel> {

    @Override
    @NotNull
    @Cacheable(value = "d_group", key = "#id")
    Optional<DGroupModel> findById(@NotNull Long id);

}