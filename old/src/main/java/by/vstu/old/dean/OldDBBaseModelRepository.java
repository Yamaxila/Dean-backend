package by.vstu.old.dean;

import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Collection;
import java.util.List;

@NoRepositoryBean
public interface OldDBBaseModelRepository<T extends OldDBBaseModel> extends JpaRepository<T, Long> {
    List<T> findAllByIdAfter(Long id);

    @NotNull
    List<T> findByIdNotIn(Collection<Long> ids);
}