package by.vstu.old.dean.repo;

import by.vstu.old.dean.OldDBBaseModelRepository;
import by.vstu.old.dean.models.DFacultyModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DFacultyModelRepository extends OldDBBaseModelRepository<DFacultyModel> {

    @Override
    @NotNull
    @Cacheable(value = "d_faculty", key = "#id")
    Optional<DFacultyModel> findById(@NotNull Long id);

}