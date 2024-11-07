package by.vstu.old.dean.services.migrate;

/**
 * Интерфейс для выполнения миграции данных.
 */
public interface IMigrateExecutor {

    /**
     * Метод для запуска миграции данных.
     */
    void migrate();

    /**
     * Метод для инициализации миграции данных.
     */
    void init();

    /**
     * Метод для очистки временных данных.
     */
    void cleanup();

}
