package by.vstu.dean.services.updates;

import by.vstu.dean.dto.BaseDTO;
import by.vstu.dean.dto.future.BaseMapperInterface;
import by.vstu.dean.enums.EStatus;
import by.vstu.dean.future.DBBaseModel;
import by.vstu.dean.future.DBBaseModelRepository;
import by.vstu.dean.old.OldDBBaseModel;
import by.vstu.dean.old.OldDBBaseModelRepository;
import by.vstu.dean.services.BaseService;
import by.vstu.dean.services.migrate.BaseMigrateService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;
import java.util.*;

@RequiredArgsConstructor
public abstract class BaseUpdateService<U extends BaseDTO, D extends OldDBBaseModel, Y extends OldDBBaseModelRepository<D>, O extends DBBaseModel, I extends BaseMapperInterface<U, O>, R extends DBBaseModelRepository<O>, S extends BaseService<U, O, I, R>, M extends BaseMigrateService<O, D>> implements IUpdateExecutor {

    protected final R repo;
    protected final Y dRepo;
    protected final M baseMigrateService;
    protected final S service;
    protected final MainUpdateService updateService;

    public boolean isEqual(O future, O old) throws IllegalAccessException {
        Class<?> clazz = future.getClass();

        for (Field field : Arrays.stream(clazz.getDeclaredFields()).filter(p ->
                !p.getName().equalsIgnoreCase("id")
                        && !p.getName().equalsIgnoreCase("updated")
                        && !p.getName().equalsIgnoreCase("status")
                        && !p.getName().equalsIgnoreCase("created")
                        && !p.getName().equalsIgnoreCase("sourceId")
                        && !p.getName().equalsIgnoreCase("approved")
                        && !p.getName().equalsIgnoreCase("hostelRoom")
                        && !p.getName().equalsIgnoreCase("hostelRoomId")
                        && !p.getName().equalsIgnoreCase("migrateDate")
        ).toList()) {

            if(field.getType().equals(Set.class) || field.getType().equals(List.class))
                continue;

            field.setAccessible(true);
            Object value1 = field.get(future);
            Object value2 = field.get(old);


            if(value1 instanceof DBBaseModel
                    && value2 instanceof DBBaseModel) {
                if (!((DBBaseModel) value1).getSourceId().equals(((DBBaseModel) value2).getSourceId()))
                    return false;
            } else {
                if(!(value1 instanceof DBBaseModel))
                    if (!Objects.equals(value1, value2)) {
                        return false;
                    }
            }
        }
        return true;
    }
    public List<O> getUpdated() {
        List<O> out = new ArrayList<>();
        this.repo.findAll().stream().filter(p -> p.getStatus().equals(EStatus.ACTIVE)).forEach(row -> {
            O temp = this.baseMigrateService.convertSingle(this.findOldModel(row.getSourceId()), true);
            try {
                if(!this.isEqual(row, temp)) {
                    temp.setId(row.getId());
                    temp.setStatus(row.getStatus());
                    temp.setSourceId(row.getSourceId());
                    out.add(temp);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        });
        return out;
    }


    protected D findOldModel(Long id) {
        Optional<D> d = this.dRepo.findById(id);

        if(d.isEmpty())
            throw new RuntimeException("Old DB lost Row with id = " + id);

        return d.get();
    }

    @Override
    public void update() {
        System.err.print(this.getClass());
        List<O> updated = this.getUpdated();
        System.err.println("  -  " + updated.size());
        this.service.saveAll(updated);
    }

    @Override
    @PostConstruct
    @Order(6)
    public void onInit() {
        System.err.println("[UPDATE REGISTER]: " + this.getClass());
        this.updateService.registerService(this);
    }
}
