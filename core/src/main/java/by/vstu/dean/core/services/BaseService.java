package by.vstu.dean.core.services;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.core.rsql.CustomRsqlVisitor;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.javers.core.Javers;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

/**
 * Базовый сервис с общими методами для работы с моделями базы данных.
 *
 * @param <O> Тип объекта модели базы данных.
 * @param <R> Тип репозитория для объекта модели базы данных.
 */
@RequiredArgsConstructor
public abstract class BaseService<O extends DBBaseModel, R extends DBBaseModelRepository<O>> {

    /**
     * Репозиторий для работы с моделью базы данных.
     */
    @Getter
    protected final R repo;

    protected final Javers javers;

    /**
     * Получает все объекты модели из базы данных.
     *
     * @return Список объектов модели.
     */
    public List<O> getAll() {
        return this.repo.findAll();
    }

    /**
     * Получает все объекты модели из базы данных.
     *
     * @return Список объектов модели.
     */
    public List<O> rsql(String rsql) {
        Node rootNode = new RSQLParser().parse(rsql);
        Specification<O> spec = rootNode.accept(new CustomRsqlVisitor<>());
        return this.repo.findAll(spec);
    }

    /**
     * Получает все активные объекты модели из базы данных.
     *
     * @return Список активных объектов модели.
     */
    public List<O> getAllActive(Boolean is) {
        return this.repo.findAllByStatus(is == null ? EStatus.ACTIVE : (is ? EStatus.ACTIVE : EStatus.DELETED));
    }

    /**
     * Получает объект модели по идентификатору.
     *
     * @param id Идентификатор объекта модели.
     * @return Optional, содержащий объект модели, если найден, иначе пустой Optional.
     */
    public Optional<O> getById(Long id) {
        if(id == null)
            return Optional.empty();
        return this.repo.findById(id);
    }

    /**
     * Получает объект модели по идентификатору.
     *
     * @param id Идентификатор объекта модели.
     * @return Объект модели, соответствующий идентификатору.
     */
    public O getBySourceId(Long id) {
        return this.repo.findBySourceId(id);
    }

    /**
     * Сохраняет объект модели в базу данных.
     *
     * @param o сущность.
     * @return Сохраненный объект модели.
     */
    public O save(O o) {
        o = this.repo.saveAndFlush(o);
        if (o != null)
            this.javers.commit("dean", o);
        return o;
    }

    /**
     * Сохраняет список объектов модели в базу данных.
     *
     * @param o Список объектов модели для сохранения.
     * @return Список сохраненных объектов модели.
     */
    public List<O> saveAll(List<O> o) {
        o = this.repo.saveAllAndFlush(o);
        if (!o.isEmpty())
            this.javers.commit("dean", o);
        return o;
    }

    /**
     * Удаляет объект модели.
     *
     * @param o Объект модели для удаления.
     * @return Удаленный объект модели.
     */
    @Deprecated
    public O delete(O o) {
        if (o.getId() == null)
            return null;

        o.setStatus(EStatus.DELETED);

        return this.save(o);
    }

    /**
     * Удаляет объект модели по идентификатору.
     *
     * @param id Идентификатор объекта модели для удаления.
     * @return Удаленный объект модели.
     */
    public O delete(Long id) {
        Optional<O> o = this.getById(id);

        if (o.isEmpty())
            return null;

        O o1 = o.get();
        o1.setStatus(EStatus.DELETED);

        return this.save(o1);
    }

}
