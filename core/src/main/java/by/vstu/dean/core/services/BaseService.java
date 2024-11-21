package by.vstu.dean.core.services;

import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.core.rsql.CustomRsqlVisitor;
import by.vstu.dean.core.websocket.WSControllerManager;
import by.vstu.dean.core.websocket.WSListener;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.javers.core.Javers;
import org.springframework.cache.annotation.Cacheable;
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

    protected final WSControllerManager wsControllerManager;

    /**
     * Получает все объекты модели из базы данных.
     *
     * @return Список объектов модели.
     */
    @Cacheable(cacheResolver = "simpleCacheResolver", unless = "#result.size() == 0")
    @Transactional
    public List<O> getAll() {
        return this.repo.findAll();
    }

    /**
     * Получает все объекты модели из базы данных.
     *
     * @return Список объектов модели.
     */
    @Cacheable(cacheResolver = "simpleCacheResolver", unless = "#result.size() == 0")
    @Transactional
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
    @Cacheable(cacheResolver = "simpleCacheResolver", unless = "#result.size() == 0")
    @Transactional
    public List<O> getAllActive(Boolean is) {
        return this.repo.findAllByStatus(is == null ? EStatus.ACTIVE : (is ? EStatus.ACTIVE : EStatus.DELETED));
    }

    /**
     * Получает объект модели по идентификатору.
     *
     * @param id Идентификатор объекта модели.
     * @return Optional, содержащий объект модели, если найден, иначе пустой Optional.
     */
    @Cacheable(cacheResolver = "simpleCacheResolver", unless = "#result == null", key = "#id")
    @Transactional
    public Optional<O> getById(Long id) {
        if(id == null)
            return Optional.empty();
        return this.repo.findById(id);
    }

    /**
     * Получает объект модели по идентификатору.
     *
     * @param sourceId Идентификатор объекта модели.
     * @return Объект модели, соответствующий идентификатору.
     */
    @Cacheable(cacheResolver = "simpleCacheResolver", unless = "#result == null || #sourceId == null", key = "#sourceId")
    public O getBySourceId(Long sourceId) {
        if (sourceId == null || sourceId == 0)
            return null;
        return this.repo.findBySourceId(sourceId);
    }

    /**
     * Сохраняет объект модели в базу данных.
     *
     * @param o сущность.
     * @return Сохраненный объект модели.
     */
    public O save(O o) {
        if (o == null)
            return null;

        O o2 = this.repo.saveAndFlush(o);
        this.javers.commit("dean-" + this.getClass().getSimpleName(), o2);

        /*
         *  Поддержка WebSocket
         *  Сохранённые данные уходят сразу всем слушателям.
         *  Поиск нужного контроллера в менеджере {@link WSControllerManager}
         */
        WSListener listener = wsControllerManager.findByClass(o.getClass());

        if (listener != null) { // Возможно, нет контроллера
            if (o.getId() != null) // Возможно, у нас будут разные случаи
                listener.onUpdate(o2);
            else
                listener.onCreate(o2);
        }


        return o2;
    }

    /**
     * Сохраняет список объектов модели в базу данных.
     *
     * @param o Список объектов модели для сохранения.
     * @return Список сохраненных объектов модели.
     */
    public List<O> saveAll(List<O> o) {
        if (o == null)
            return null;

        if (o.isEmpty())
            return o;

        List<O> o2 = this.repo.saveAllAndFlush(o);
        this.javers.commit("dean-" + this.getClass().getSimpleName(), o2);

        /*
         *  Поддержка WebSocket
         *  Сохранённые данные уходят сразу всем слушателям.
         *  Поиск нужного контроллера в менеджере {@link WSControllerManager}
         */
        WSListener listener = wsControllerManager.findByClass(o.getClass());

        if (listener != null) {
            if (o.stream().findFirst().get().getId() != null)
                listener.onUpdate(o2);
            else
                listener.onCreate(o2);
        }

        return o2;
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
        /*
         *  Поддержка WebSocket
         *  Сохранённые данные уходят сразу всем слушателям.
         *  Поиск нужного контроллера в менеджере {@link WSControllerManager}
         */
        WSListener listener = wsControllerManager.findByClass(o.getClass());

        if (listener != null) {
            listener.onDelete(o);
        }

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

        WSListener listener = wsControllerManager.findByClass(o1.getClass());

        if (listener != null) {
            listener.onDelete(o1);
        }

        return this.save(o1);
    }

}