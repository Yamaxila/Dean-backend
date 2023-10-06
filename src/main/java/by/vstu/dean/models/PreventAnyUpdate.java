package by.vstu.dean.models;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

/**
 * Класс для предотвращения любых изменений сущностей JPA.
 */
public class PreventAnyUpdate {

    /**
     * Предперсистентное действие.
     *
     * @param o Объект
     */
    @PrePersist
    void onPrePersist(Object o) {
        throw new IllegalStateException("JPA пытается сохранить сущность типа " + (o == null ? "null" : o.getClass()));
    }

    /**
     * Предобновляющее действие.
     *
     * @param o Объект
     */
    @PreUpdate
    void onPreUpdate(Object o) {
        throw new IllegalStateException("JPA пытается обновить сущность типа " + (o == null ? "null" : o.getClass()));
    }

    /**
     * Предудаляющее действие.
     *
     * @param o Объект
     */
    @PreRemove
    void onPreRemove(Object o) {
        throw new IllegalStateException("JPA пытается удалить сущность типа " + (o == null ? "null" : o.getClass()));
    }
}
