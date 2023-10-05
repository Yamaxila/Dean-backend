package by.vstu.dean.services;

import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.DBBaseModelRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public abstract class BaseService<O extends DBBaseModel, R extends DBBaseModelRepository<O>> {

    protected final R repo;

    public List<O> getAll() {
        return this.repo.findAll();
    }
    public Optional<O> getById(Long id) {
        return this.repo.findById(id);
    }

    public O getBySourceId(Long id) {
        return (O)this.repo.findBySourceId(id);
    }

    public O save(O o) {
        return this.repo.saveAndFlush(o);
    }
    public List<O> saveAll(List<O> o) {
        return this.repo.saveAllAndFlush(o);
    }
    public O delete(O o) {
        if(o.getId() == null)
            return null;

        o.setStatus(EStatus.DELETED);

        return this.repo.saveAndFlush(o);
    }

    public O delete(Long id) {
        Optional<O> o = this.getById(id);

        if(!o.isPresent())
            return null;

        O o1 = o.get();
        o1.setStatus(EStatus.DELETED);

        return this.repo.saveAndFlush(o1);
    }
}
