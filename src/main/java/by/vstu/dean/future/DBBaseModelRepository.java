package by.vstu.dean.future;

import by.vstu.dean.enums.EStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface DBBaseModelRepository<T extends DBBaseModel> extends JpaRepository<T, Long> {

    public DBBaseModel findTopByOrderByIdDesc();

    <O extends DBBaseModel> List<O> findBySourceId(Long sourceId);

    <O extends DBBaseModel> List<O> findAllByStatus(EStatus status);
}