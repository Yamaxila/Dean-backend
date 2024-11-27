package by.vstu.old.dean.services.update;

import by.vstu.dean.core.anotations.ReflectionIgnore;
import by.vstu.dean.core.enums.EStatus;
import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.core.repo.DBBaseModelRepository;
import by.vstu.dean.core.services.BaseService;
import by.vstu.dean.core.utils.ReflectionUtils;
import by.vstu.old.dean.OldDBBaseModel;
import by.vstu.old.dean.OldDBBaseModelRepository;
import by.vstu.old.dean.services.migrate.BaseMigrateService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Абстрактный базовый класс для служб обновления (update service).
 * Этот класс предоставляет основные методы для сравнения и обновления данных в будущей и старой версиях моделей.
 *
 * @param <D> Тип модели старой версии.
 * @param <Y> Репозиторий для работы с моделями старой версии.
 * @param <O> Тип модели будущей версии.
 * @param <R> Репозиторий для работы с моделями будущей версии.
 * @param <S> Сервис для работы с моделями будущей версии.
 * @param <M> Сервис миграции данных между старой и будущей версией моделей.
 */
@RequiredArgsConstructor
public abstract class BaseUpdateService<
        D extends OldDBBaseModel
        , Y extends OldDBBaseModelRepository<D>
        , O extends DBBaseModel
        , R extends DBBaseModelRepository<O>
        , S extends BaseService<O, R>
        , M extends BaseMigrateService<O, D>>
        implements IUpdateExecutor {

    // Конструктор класса
    protected final R repo;
    protected final Y dRepo;
    protected final M baseMigrateService;
    protected final S service;
//    protected final MainUpdateService updateService;

    /**
     * Метод для сравнения двух объектов модели будущей версии и старой версии.
     * Сравниваются все поля, кроме тех, которые указаны в методе для игнорирования.
     *
     * @param future Модель будущей версии.
     * @param old    Модель старой версии.
     * @return true, если модели идентичны, false в противном случаи.
     * @throws IllegalAccessException Исключение, бросаемое при недоступности поля для сравнения.
     */
    public boolean isEqual(O future, O old) throws IllegalAccessException {
        Class<?> clazz = future.getClass();
        boolean equals = true;

        for (Field field : ReflectionUtils.getAllFields(clazz).stream().filter(p ->
                !p.getName().equalsIgnoreCase("id")
                        && !p.getName().equalsIgnoreCase("updated")
                        && !p.getName().equalsIgnoreCase("created")
                        && !p.getName().equalsIgnoreCase("sourceId")
                        && !p.getName().equalsIgnoreCase("approved")
                        && !p.getName().equalsIgnoreCase("hostelRoom")
                        && !p.getName().equalsIgnoreCase("hostelRoomId")
                        && !p.getName().equalsIgnoreCase("migrateDate")
                        && Arrays.stream(p.getAnnotations()).noneMatch(a -> a.annotationType() == ReflectionIgnore.class)
        ).toList()) {

            if (field.getType().equals(Set.class) || field.getType().equals(List.class))
                continue;

            field.setAccessible(true);
            Object value1 = field.get(future);
            Object value2 = field.get(old);

            if (value1 instanceof DBBaseModel && value2 instanceof DBBaseModel) {
                if (((DBBaseModel) value1).getSourceId() != null && ((DBBaseModel) value2).getSourceId() != null)
                    if (!((DBBaseModel) value1).getSourceId().equals(((DBBaseModel) value2).getSourceId())) {
                        field.set(future, value2);
                        equals = false;
                    }
            } else {
                if (!(value1 instanceof DBBaseModel)) {
                    if (!Objects.equals(value1, value2)) {
                        field.set(future, value2);
                        equals = false;
                    }
                }
            }
        }
        return equals;
    }

    /**
     * Метод для получения списка моделей будущей версии, требующих обновления.
     * Модели выбираются из репозитория и фильтруются по статусу ACTIVE.
     *
     * @return Список моделей, требующих обновления.
     */
    public List<O> getUpdated() {
        List<O> out = Collections.synchronizedList(new ArrayList<>());
        this.repo.findAll().stream().filter(p -> p.getStatus().equals(EStatus.ACTIVE)).forEach(row -> {
            D t = this.findOldModel(row.getSourceId());
            if (t != null) {
                O temp = this.baseMigrateService.convertSingle(t, true);
                try {
                    if (!this.isEqual(row, temp)) {
                        out.add(row);
                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            } else {
                //НЕЛЬЗЯ УДАЛЯТЬ ДАННЫЕ
                row.setStatus(EStatus.DELETED);
                out.add(row);
            }
        });
        out.forEach(o -> o.setUpdated(LocalDateTime.now()));
        return out;
    }

    /**
     * Метод для поиска старой версии модели по её идентификатору.
     *
     * @param id Идентификатор старой версии модели.
     * @return Модель старой версии или null, если модель не найдена.
     */
    protected D findOldModel(Long id) {
        if (id == null)
            return null;

        Optional<D> d = this.dRepo.findById(id);

        if (d.isEmpty()) {
            System.err.println("Старая версия БД: не найдена запись с идентификатором " + id);
            return null;
        }

        return d.get();
    }

    /**
     * Метод для обновления данных. Вызывается для выполнения обновления.
     * Выбирает модели, требующие обновления, и сохраняет их.
     */
    @Override
    public void update() {
        System.err.print(this.getClass());
        List<O> updated = this.getUpdated();
        System.err.println("  -  " + updated.size());

        System.err.println(Arrays.toString(updated.stream().map(DBBaseModel::getId).toArray()));

        this.service.saveAll(updated);
    }

    /**
     * Метод, выполняемый после создания экземпляра класса (постконструктор).
     * Регистрирует службу обновления в главной службе обновления.
     */
    @Override
    @PostConstruct
    @Order(0)
    public void onInit() {
//        System.out.println("[UPDATE REGISTER]: " + this.getClass());
//        this.updateService.registerService(this);
    }
}
