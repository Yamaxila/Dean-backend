package by.vstu.old.dean.repo;

import by.vstu.old.dean.OldDBBaseModelRepository;
import by.vstu.old.dean.models.DCitizenshipModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DCitizenshipModelRepository extends OldDBBaseModelRepository<DCitizenshipModel> {

    @Override
    @NotNull
    @Cacheable(value = "d_citizenship", key = "#id")
    Optional<DCitizenshipModel> findById(@NotNull Long id);

}