package by.vstu.dean.core.services;

import by.vstu.dean.core.utils.ReflectionUtils;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LazyService {

    private final EntityManager entityManager;

    @Value("${lazy.depth}")
    private Integer defaultDepth;

    public Object initializeLazy(Object model) {
        return this.initializeLazy(model, this.defaultDepth);
    }

    public Object initializeLazy(Object model, int depth) {
        //сначала уменьшаем глубину
        --depth;
        int finalDepth = depth;
        //Получаем только те поля, что мы можем проинициализировать
        ReflectionUtils.getAllInitializableFields(model.getClass()).stream()
                .peek(m -> m.setAccessible(true)).filter(p -> {
                    try {
                        return (p.get(model) instanceof HibernateProxy);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                }).forEach(i -> {
                    try {
                        HibernateProxy proxy = (HibernateProxy) i.get(model);
                        Object o = this.entityManager.find(proxy.getHibernateLazyInitializer().getPersistentClass(), proxy.getHibernateLazyInitializer().getIdentifier());
                        i.set(model, this.initializeLazy(o, finalDepth));
                    } catch (Exception ignored) {
                        ignored.printStackTrace();
                    }

                });
        return model;
    }

    public List<Object> initializeLazyList(List<Object> models, int depth) {
        return models.stream().map((model) -> initializeLazy(model, depth)).toList();
    }

    public List<Object> initializeLazyList(List<Object> models) {
        return this.initializeLazyList(models, this.defaultDepth);
    }

    public List<Object> reverseInitLazyList(Class<?> type, Long reverseId) {
        Object o = this.entityManager.find(type, reverseId);

        if (o == null)
            return new ArrayList<>();

        if (o instanceof List<?>)
            return (List<Object>) o;
        else
            return List.of(o);
    }

}
