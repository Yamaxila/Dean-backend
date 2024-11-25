package by.vstu.dean.core.repo;

import jakarta.persistence.EntityManager;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;


@Slf4j
public class NotSimpleJPARepository<T, ID> extends SimpleJpaRepository<T, ID> {
    private final EntityManager entityManager;

    private static final Integer POOL_SIZE = 10;

    public NotSimpleJPARepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public @NotNull List<T> findAll() {
        ExecutorService executor = Executors.newFixedThreadPool(NotSimpleJPARepository.POOL_SIZE);
        long count = this.count(); //Общее кол-во записей
        long pageSize = count / NotSimpleJPARepository.POOL_SIZE; //страница = кол-ву записей деленному на кол-во потоков
        long totalPages = (long) Math.ceil((double) count / pageSize); // получаем итоговое кол-во страниц
        if (pageSize == 0) {
            pageSize = count;
            totalPages = 1;
        }
        final long finalPageSize = pageSize;
        List<Future<List<T>>> futures = new ArrayList<>();
        //Создаем потоки
        for (int page = 0; page < totalPages; page++) {
            final int currentPage = page; // текущая страница
            //создаем задачу для постраничного поиска на текущей странице
            Callable<List<T>> task = () -> this.findAllByPage(currentPage, (int) finalPageSize);
            futures.add(executor.submit(task)); // выполняем и добавляем в список ожидания
        }

        return futures.parallelStream().map(m -> {
            try {
                return m.get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }).flatMap(Collection::stream).toList();
    }

    private List<T> findAllByPage(int page, int size) {
        return this.entityManager.createQuery("SELECT e FROM " + getDomainClass().getSimpleName() + " e", getDomainClass())
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }
}
