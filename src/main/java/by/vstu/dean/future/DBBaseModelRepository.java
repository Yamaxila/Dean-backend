package by.vstu.dean.future;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface DBBaseModelRepository<T extends DBBaseModel> extends JpaRepository<T, Long> {

    public DBBaseModel findTopByOrderByIdDesc();



}