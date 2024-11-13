package by.vstu.dean.core.services;

import by.vstu.dean.core.models.DBBaseModel;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LazyService<O extends DBBaseModel> {

    private final EntityManager entityManager;


    public O initializeLazy(O model) {

        FieldUtils.getAllFieldsList(model.getClass()).stream()
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
                        i.set(model, o);
                    } catch (Exception ignored) {
                        ignored.printStackTrace();
                    }

                });
        return model;
    }

    public List<O> initializeLazyList(List<O> models) {
        return models.stream().map(this::initializeLazy).toList();
    }

}
