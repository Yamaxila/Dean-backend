package by.vstu.dean.core.services;

import by.vstu.dean.core.dto.BaseDTO;
import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.core.models.mapper.BaseMapperInterface;
import by.vstu.dean.core.rsql.CustomRsqlVisitor;
import by.vstu.dean.core.repo.DBBaseModelRepository;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.Node;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

/**
 * Базовый сервис с общими методами для работы с моделями базы данных.
 *
 * @param <D> Тип DTO объекта модели базы данных
 * @param <O> Тип объекта модели базы данных.
 * @param <M> Маппер модель-DTO и обратно.
 * @param <R> Тип репозитория для объекта модели базы данных.
 */
@RequiredArgsConstructor
public abstract class BaseService<D extends BaseDTO, O extends DBBaseModel, M extends BaseMapperInterface<D, O>, R extends DBBaseModelRepository<O>> {

    /**
     * Репозиторий для работы с моделью базы данных.
     */
    protected final R repo;

    /**
     * Маппер.
     */
    protected final M mapper;

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
        return this.repo.saveAndFlush(o);
    }

    public D update(D dto) {
        if (this.repo.findById(dto.getId()).isEmpty())
            return this.mapper.toDto(this.save(this.mapper.toEntity(dto)));
        return this.mapper.toDto(this.repo.saveAndFlush(this.mapper.partialUpdate(dto, this.repo.findById(dto.getId()).get())));
    }

    /**
     * Сохраняет список объектов модели в базу данных.
     *
     * @param o Список объектов модели для сохранения.
     * @return Список сохраненных объектов модели.
     */
    public List<O> saveAll(List<O> o) {
        return this.repo.saveAllAndFlush(o);
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

        return this.repo.saveAndFlush(o);
    }

    /**
     * Удаляет объект модели по идентификатору.
     *
     * @param id Идентификатор объекта модели для удаления.
     * @return Удаленный объект модели.
     */
    public O delete(Long id) {
        Optional<O> o = this.repo.findById(id);

        if (o.isEmpty())
            return null;

        O o1 = o.get();
        o1.setStatus(EStatus.DELETED);

        return this.repo.saveAndFlush(o1);
    }

    public O toEntity(D dto) {
        return this.mapper.toEntity(dto);
    }

    public List<O> toEntity(List<D> dto) {
        return this.mapper.toEntity(dto);
    }

    public D toDto(O o) {
        return this.mapper.toDto(o);
    }

    public List<D> toDto(List<O> o) {
        return this.mapper.toDto(o);
    }
}
