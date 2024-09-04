package by.vstu.dean.services.update;

import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface IUpdateExecutor {

    void update();

    @SuppressWarnings("unused")
    void onInit();
}
