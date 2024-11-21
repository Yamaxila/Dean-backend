package by.vstu.old.dean;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

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
