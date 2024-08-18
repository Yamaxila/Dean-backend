package by.vstu.dean.old.services.migrate;

import by.vstu.dean.core.models.DBBaseModel;
import by.vstu.dean.old.OldDBBaseModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Базовый класс для сервисов миграции данных.
 *
 * @param <S> Тип модели новой базы данных.
 * @param <T> Тип модели старой базы данных.
 */
public abstract class BaseMigrateService<S extends DBBaseModel, T extends OldDBBaseModel> implements IMigrateExecutor {

    /**
     * Возвращает последний идентификатор в старой базе данных.
     *
     * @return Последний идентификатор в старой базе данных.
     */
    public abstract Long getLastDBId();

    /**
     * Конвертирует отсутствующие записи из старой базы данных в новую.
     *
     * @return Список новых записей, сконвертированных из старой базы данных.
     */
    public abstract List<S> convertNotExistsFromDB();

    /**
     * Конвертирует одиночную запись из старой базы данных в новую.
     *
     * @param t Модель старой базы данных.
     * @return Конвертированная модель новой базы данных.
     */
    public S convertSingle(T t) {
        return this.convertSingle(t, false);
    }

    public abstract S convertSingle(T t, boolean update);

    /**
     * Конвертирует список записей из старой базы данных в новую.
     *
     * @param t Список моделей старой базы данных.
     * @return Список сконвертированных моделей новой базы данных.
     */
    public List<S> convertList(List<T> t) {
        List<S> out = new ArrayList<>();
        t.forEach(tt -> out.add(this.convertSingle(tt)));
        return out;
    }

    /**
     * Вставляет одиночную запись в новую базу данных.
     *
     * @param t Модель новой базы данных.
     * @return Вставленная модель новой базы данных.
     */
    public abstract S insertSingle(S t);

    /**
     * Вставляет список записей в новую базу данных.
     *
     * @param t Список моделей новой базы данных.
     * @return Список вставленных моделей новой базы данных.
     */
    public abstract List<S> insertAll(List<S> t);
}
