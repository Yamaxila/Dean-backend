package by.vstu.old.dean.repo;

import by.vstu.old.dean.OldDBBaseModelRepository;
import by.vstu.old.dean.models.DDepartmentModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DDepartmentModelRepository extends OldDBBaseModelRepository<DDepartmentModel> {

    @Override
    @NotNull
    @Cacheable(value = "d_department", key = "#id")
    Optional<DDepartmentModel> findById(@NotNull Long id);

}